package com.banco.servicio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaccion(
    UUID id,
    String cuentaOrigen,
    String cuentaDestino,
    BigDecimal monto,
    LocalDateTime fecha,
    String tipo,
    String estado,
    String descripcion) {

    public Transaccion {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("La cuenta de origen no puede ser nula o vacía");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException("La cuenta de destino no puede ser nula o vacía");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de transacción no puede ser nulo o vacío");
        }
        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }
    }

    public Transaccion withEstado(String nuevoEstado) {
        return new Transaccion(
            this.id,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.fecha,
            this.tipo,
            nuevoEstado,
            this.descripcion
        );
    }
}