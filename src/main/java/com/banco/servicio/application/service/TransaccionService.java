package com.banco.servicio.application.service;

import com.banco.servicio.domain.model.Transaccion;
import com.banco.servicio.domain.port.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransaccionService {

    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);
    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public Mono<Transaccion> crearTransaccion(Transaccion transaccion) {
        if (transaccion == null) {
            return Mono.error(new IllegalArgumentException("La transacción no puede ser nula"));
        }
        if (transaccion.cuentaOrigen() == null || transaccion.cuentaOrigen().isBlank()) {
            return Mono.error(new IllegalArgumentException("La cuenta de origen es obligatoria"));
        }
        if (transaccion.cuentaDestino() == null || transaccion.cuentaDestino().isBlank()) {
            return Mono.error(new IllegalArgumentException("La cuenta de destino es obligatoria"));
        }
        if (transaccion.cuentaOrigen().equals(transaccion.cuentaDestino())) {
            return Mono.error(new IllegalArgumentException("La cuenta de origen y destino no pueden ser iguales"));
        }
        if (transaccion.monto() == null || transaccion.monto().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new IllegalArgumentException("El monto debe ser mayor a cero"));
        }

        Transaccion transaccionNueva = new Transaccion(
            UUID.randomUUID(),
            transaccion.cuentaOrigen(),
            transaccion.cuentaDestino(),
            transaccion.monto(),
            transaccion.tipo() != null ? transaccion.tipo() : "TRANSFERENCIA",
            "PENDIENTE",
            LocalDateTime.now(),
            null
        );

        log.info("Creando transacción: {} -> {}, monto: {}", 
            transaccionNueva.cuentaOrigen(), 
            transaccionNueva.cuentaDestino(), 
            transaccionNueva.monto());

        return transaccionRepository.save(transaccionNueva)
            .doOnSuccess(t -> log.info("Transacción creada exitosamente con ID: {}", t.id()))
            .doOnError(e -> log.error("Error al crear transacción: {}", e.getMessage()));
    }

    public Mono<Transaccion> obtenerTransaccionPorId(UUID id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        log.info("Buscando transacción por ID: {}", id);
        return transaccionRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)));
    }

    public Flux<Transaccion> listarTodasLasTransacciones() {
        log.info("Listando todas las transacciones");
        return transaccionRepository.findAll();
    }

    public Flux<Transaccion> listarTransaccionesPorCuentaOrigen(String cuentaOrigen) {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            return Flux.error(new IllegalArgumentException("La cuenta de origen es obligatoria"));
        }
        log.info("Listando transacciones por cuenta de origen: {}", cuentaOrigen);
        return transaccionRepository.findByCuentaOrigen(cuentaOrigen);
    }

    public Flux<Transaccion> listarTransaccionesPorCuentaDestino(String cuentaDestino) {
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            return Flux.error(new IllegalArgumentException("La cuenta de destino es obligatoria"));
        }
        log.info("Listando transacciones por cuenta de destino: {}", cuentaDestino);
        return transaccionRepository.findByCuentaDestino(cuentaDestino);
    }

    public Flux<Transaccion> listarTransaccionesPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            return Flux.error(new IllegalArgumentException("El tipo de transacción es obligatorio"));
        }
        log.info("Listando transacciones por tipo: {}", tipo);
        return transaccionRepository.findByTipo(tipo.toUpperCase());
    }

    public Flux<Transaccion> listarTransaccionesPorEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            return Flux.error(new IllegalArgumentException("El estado es obligatorio"));
        }
        log.info("Listando transacciones por estado: {}", estado);
        return transaccionRepository.findByEstado(estado.toUpperCase());
    }

    public Mono<Transaccion> actualizarEstadoTransaccion(UUID id, String nuevoEstado) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            return Mono.error(new IllegalArgumentException("El nuevo estado es obligatorio"));
        }

        String estadoNormalizado = nuevoEstado.toUpperCase();
        if (!esEstadoValido(estadoNormalizado)) {
            return Mono.error(new IllegalArgumentException("Estado inválido. Estados válidos: PENDIENTE, PROCESADA, RECHAZADA, COMPLETADA"));
        }

        log.info("Actualizando estado de transacción {} a {}", id, estadoNormalizado);

        return transaccionRepository.findById(id)
            .flatMap(transaccionExistente -> {
                Transaccion transaccionActualizada = transaccionExistente.withEstado(estadoNormalizado);
                return transaccionRepository.save(transaccionActualizada);
            })
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)))
            .doOnSuccess(t -> log.info("Estado actualizado exitosamente a: {}", t.estado()))
            .doOnError(e -> log.error("Error al actualizar estado: {}", e.getMessage()));
    }

    public Mono<Void> eliminarTransaccion(UUID id) {
        if (id == null) {
            return Mono.error(new IllegalArgumentException("El ID no puede ser nulo"));
        }
        log.info("Eliminando transacción con ID: {}", id);
        return transaccionRepository.findById(id)
            .flatMap(transaccionExistente -> transaccionRepository.deleteById(id)
                .doOnSuccess(v -> log.info("Transacción eliminada exitosamente: {}", id))
                .doOnError(e -> log.error("Error al eliminar transacción: {}", e.getMessage())))
            .switchIfEmpty(Mono.error(new RuntimeException("Transacción no encontrada con ID: " + id)));
    }

    private boolean esEstadoValido(String estado) {
        return estado.equals("PENDIENTE") || 
               estado.equals("PROCESADA") || 
               estado.equals("RECHAZADA") || 
               estado.equals("COMPLETADA");
    }
}