package com.banco.servicio.infrastructure.controller;

import com.banco.servicio.application.service.TransaccionService;
import com.banco.servicio.domain.model.Transaccion;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public Mono<ResponseEntity<Transaccion>> crearTransaccion(@Valid @RequestBody Transaccion transaccion) {
        log.info("Petición POST para crear transacción");
        return transaccionService.crearTransaccion(transaccion)
            .map(transaccionCreada -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccionCreada))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al crear transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al crear transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transaccion>> obtenerTransaccionPorId(@PathVariable UUID id) {
        log.info("Petición GET para obtener transacción con ID: {}", id);
        return transaccionService.obtenerTransaccionPorId(id)
            .map(transaccion -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccion))
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Transacción no encontrada: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @GetMapping
    public Flux<Transaccion> listarTodasLasTransacciones() {
        log.info("Petición GET para listar todas las transacciones");
        return transaccionService.listarTodasLasTransacciones();
    }

    @GetMapping(params = "cuentaOrigen")
    public Flux<Transaccion> listarTransaccionesPorCuentaOrigen(@RequestParam String cuentaOrigen) {
        log.info("Petición GET para listar transacciones por cuenta origen: {}", cuentaOrigen);
        return transaccionService.listarTransaccionesPorCuentaOrigen(cuentaOrigen)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "cuentaDestino")
    public Flux<Transaccion> listarTransaccionesPorCuentaDestino(@RequestParam String cuentaDestino) {
        log.info("Petición GET para listar transacciones por cuenta destino: {}", cuentaDestino);
        return transaccionService.listarTransaccionesPorCuentaDestino(cuentaDestino)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "tipo")
    public Flux<Transaccion> listarTransaccionesPorTipo(@RequestParam String tipo) {
        log.info("Petición GET para listar transacciones por tipo: {}", tipo);
        return transaccionService.listarTransaccionesPorTipo(tipo)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @GetMapping(params = "estado")
    public Flux<Transaccion> listarTransaccionesPorEstado(@RequestParam String estado) {
        log.info("Petición GET para listar transacciones por estado: {}", estado);
        return transaccionService.listarTransaccionesPorEstado(estado)
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación: {}", e.getMessage());
                return Flux.empty();
            });
    }

    @PatchMapping("/{id}/estado")
    public Mono<ResponseEntity<Transaccion>> actualizarEstadoTransaccion(
            @PathVariable UUID id, 
            @RequestParam String nuevoEstado) {
        log.info("Petición PATCH para actualizar estado de transacción {} a {}", id, nuevoEstado);
        return transaccionService.actualizarEstadoTransaccion(id, nuevoEstado)
            .map(transaccion -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(transaccion))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al actualizar estado: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al actualizar estado de transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarTransaccion(@PathVariable UUID id) {
        log.info("Petición DELETE para eliminar transacción con ID: {}", id);
        return transaccionService.eliminarTransaccion(id)
            .then(Mono.just(ResponseEntity.noContent().build()))
            .onErrorResume(IllegalArgumentException.class, e -> {
                log.warn("Error de validación al eliminar: {}", e.getMessage());
                return Mono.just(ResponseEntity.badRequest().build());
            })
            .onErrorResume(RuntimeException.class, e -> {
                log.error("Error al eliminar transacción: {}", e.getMessage());
                return Mono.just(ResponseEntity.notFound().build());
            });
    }
}