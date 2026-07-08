package com.utp.sistemaclinicaveterinaria.modulos.Turno;
import java.time.*;

public interface TurnoDTO {
    // catalogo
    record TurnoCatalogResponse(
            Integer idTurno,
            String turno) {
    }

    // lista
    record TurnoListResponse(
            Integer idTurno,
            String nombre,
            LocalTime horaInicio,
            LocalTime horaFin,
            LocalDateTime fechaCreacion,
            Boolean estado) {
    }

    // detalle
    record TurnoDetailResponse(
            String nombre,
            LocalTime horaInicio,
            LocalTime horaFin,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    // crear
    record TurnoCreateRequest(
            String nombre,
            LocalTime horaInicio,
            LocalTime horaFin) {
    }

    // actualizar
    record TurnoUpdateRequest(
            String nombre,
            LocalTime horaInicio,
            LocalTime horaFin,
            Boolean estado) {
    }

    // filtro
    record TurnoFilterRequest(
            String nombre,
            Boolean estado) {
    }

    // eliminar(soft-delete)
    record TurnoDeleteRequest(
            Integer idTurno) {
    }
}
