package com.banco.servicio.infrastructure.repository;

import com.banco.servicio.domain.model.Transaccion;
import com.banco.servicio.domain.port.TransaccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface JpaTransaccionRepository extends JpaRepository<TransaccionEntity, UUID>, TransaccionRepository {

    @Override
    default Mono<Transaccion> save(Transaccion transaccion) {
        return Mono.fromCallable(() -> {
            TransaccionEntity entity = TransaccionEntity.fromDomain(transaccion);
            TransaccionEntity savedEntity = save(entity);
            return savedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    default Mono<Transaccion> findById(UUID id) {
        return Mono.fromCallable(() -> findById(id).orElse(null))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(entity -> entity != null ? Mono.just(entity.toDomain()) : Mono.empty());
    }

    @Override
    default Flux<Transaccion> findByCuentaOrigen(String cuentaOrigen) {
        return Flux.fromIterable(findByCuentaOrigenInternal(cuentaOrigen))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByCuentaDestino(String cuentaDestino) {
        return Flux.fromIterable(findByCuentaDestinoInternal(cuentaDestino))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByTipo(String tipo) {
        return Flux.fromIterable(findByTipoInternal(tipo))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findByEstado(String estado) {
        return Flux.fromIterable(findByEstadoInternal(estado))
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Flux<Transaccion> findAll() {
        return Flux.fromIterable(findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .map(TransaccionEntity::toDomain);
    }

    @Override
    default Mono<Void> deleteById(UUID id) {
        return Mono.fromRunnable(() -> deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    // Métodos internos para JPA
    default TransaccionEntity save(TransaccionEntity entity) {
        return save(entity);
    }

    default java.util.Optional<TransaccionEntity> findById(UUID id) {
        return findById(id);
    }

    default java.util.List<TransaccionEntity> findByCuentaOrigenInternal(String cuentaOrigen) {
        return findAll().stream()
                .filter(t -> cuentaOrigen.equals(t.getCuentaOrigen()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByCuentaDestinoInternal(String cuentaDestino) {
        return findAll().stream()
                .filter(t -> cuentaDestino.equals(t.getCuentaDestino()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByTipoInternal(String tipo) {
        return findAll().stream()
                .filter(t -> tipo.equals(t.getTipo()))
                .collect(Collectors.toList());
    }

    default java.util.List<TransaccionEntity> findByEstadoInternal(String estado) {
        return findAll().stream()
                .filter(t -> estado.equals(t.getEstado()))
                .collect(Collectors.toList());
    }
}