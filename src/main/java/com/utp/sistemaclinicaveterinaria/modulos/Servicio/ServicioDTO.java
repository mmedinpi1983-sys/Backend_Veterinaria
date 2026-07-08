package com.utp.sistemaclinicaveterinaria.modulos.Servicio;
import java.time.*;
import java.math.BigDecimal;

public interface ServicioDTO {

    record ServicioCatalogResponse(
            Integer idServicio,
            String nombre) {
    }

    record ServicioListResponse(
            Integer idServicio,
            String nombre,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record ServicioDetailResponse(
            Integer idServicio,
            String nombre,
            Integer idCategoria,
            BigDecimal precio,
            Boolean requiereTriaje,
            Boolean estado,
            Integer duracionEstimada,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record ServicioCreateRequest(
            String nombre,
            Integer idCategoria,
            BigDecimal precio,
            Boolean requiereTriaje,
            Integer duracionEstimada) {
    }

    record ServicioUpdateRequest(
            String nombre,
            Integer idCategoria,
            BigDecimal precio,
            Boolean requiereTriaje,
            Boolean estado,
            Integer duracionEstimada) {
    }

    record ServicioFilterRequest(
            String nombre,
            Boolean estado) {
    }

    record ServicioDeleteRequest(
            Integer idServicio) {
    }
}
