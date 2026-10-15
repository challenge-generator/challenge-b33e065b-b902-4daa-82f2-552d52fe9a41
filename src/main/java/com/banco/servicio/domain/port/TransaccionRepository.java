package com.banco.servicio.domain.port;

import com.banco.servicio.domain.model.Transaccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface TransaccionRepository {
    Mono<Transaccion> save(Transaccion transaccion);
    Mono<Transaccion> findById(UUID id);
    Flux<Transaccion> findByCuentaOrigen(String cuentaOrigen);
    Flux<Transaccion> findByCuentaDestino(String cuentaDestino);
    Flux<Transaccion> findByTipo(String tipo);
    Flux<Transaccion> findByEstado(String estado);
    Flux<Transaccion> findAll();
    Mono<Void> deleteById(UUID id);
}