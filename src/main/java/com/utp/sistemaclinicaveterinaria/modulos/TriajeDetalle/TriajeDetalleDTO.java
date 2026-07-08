package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import java.time.*;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public interface TriajeDetalleDTO {

    record TriajeDetalleCatalogResponse(
            Integer idTriajeDetalle,
            String observaciones) {
    }

    record TriajeDetalleListResponse(
            Integer idTriajeDetalle,
            String observaciones,
            String alergias,
            LocalDateTime fechaCreacion) {
    }

    record TriajeDetalleDetailResponse(
            Integer idTriajeDetalle,
            BigDecimal temperatura,
            BigDecimal peso,
            String observaciones,
            String alergias,
            Integer idTriaje,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record TriajeDetalleCreateRequest(
            @DecimalMin(value = "1.0", message = "La temperatura debe ser mayor a 1°C")
            @DecimalMax(value = "50.0", message = "La temperatura no puede superar 50°C")
            BigDecimal temperatura,
            @Positive(message = "El peso debe ser mayor a 0")
            BigDecimal peso,
            String observaciones,
            String alergias,
            Integer idTriaje) {
    }

    record TriajeDetalleUpdateRequest(
            BigDecimal temperatura,
            BigDecimal peso,
            String observaciones,
            String alergias) {
    }

    record TriajeDetalleFilterRequest() {
    }

    record TriajeDetalleDeleteRequest(
            Integer idTriajeDetalle) {
    }
}
