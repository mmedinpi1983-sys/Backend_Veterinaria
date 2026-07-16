package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface MovimientoProductoDTO {

    record MovimientoListResponse(
            Integer idMovimiento, LocalDateTime fecha, String producto, String tipo,
            Integer cantidad, Integer stockAnterior, Integer stockNuevo, String motivo, String empleado) {
    }

    record MovimientoStatsResponse(Integer entradas, Integer salidas, Integer ajustes) {
    }

    record ClaseResponse(Integer idClaseMovimiento, String descripcion) {
    }

    record MotivoResponse(Integer idMotivoMovimiento, String descripcion) {
    }

    record MovimientoCreateRequest(
            @NotNull(message = "El producto es obligatorio") Integer idProducto,
            @NotNull(message = "El tipo de movimiento es obligatorio") Integer idClaseMovimiento,
            @NotNull(message = "La cantidad es obligatoria")
            @Positive(message = "La cantidad debe ser mayor a 0") Integer cantidad,
            @NotNull(message = "El motivo es obligatorio") Integer idMotivoMovimiento,
            String observaciones) {
    }

    record MovimientoFilterRequest(String q, Integer idClaseMovimiento) {
    }
}
