package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import java.time.*;

public interface EstadoProgramacionDTO {

    record EstadoProgramacionCatalogResponse(
            Integer idEstadoProgramacion,
            String nombre) {
    }

    record EstadoProgramacionListResponse(
            Integer idEstadoProgramacion,
            String nombre,
            String descripcion,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record EstadoProgramacionDetailResponse(
            Integer idEstadoProgramacion,
            String nombre,
            String descripcion,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record EstadoProgramacionCreateRequest(
            String nombre,
            String descripcion) {
    }

    record EstadoProgramacionUpdateRequest(
            String nombre,
            String descripcion,
            Boolean estado) {
    }

    record EstadoProgramacionFilterRequest(
            String nombre,
            Boolean estado) {
    }

    record EstadoProgramacionDeleteRequest(
            Integer idEstadoProgramacion) {
    }
}
