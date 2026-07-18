package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import java.time.*;

public interface TriajeDTO {

    record TriajeCatalogResponse(
            Integer idTriaje,
            String codigoTemporal,
            Integer prioridad,
            Boolean estado) {
    }

    record TriajeListResponse(
            Integer idTriaje,
            String codigoTemporal,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record TriajeDetailResponse(
            Integer idTriaje,
            Integer idCitaProgramada,
            String codigoTemporal,
            Integer idMascota,
            Integer prioridad,
            Boolean estado,
            Integer idMetodoIngreso,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record TriajeCreateRequest(
            Integer idCitaProgramada,
            String codigoTemporal,
            Integer idMascota,
            Integer prioridad,
            Integer idMetodoIngreso) {
    }

    record TriajeCreateResponse(
            Integer idTriaje) {
    }

    record TriajeUpdateRequest(
            Integer idCitaProgramada,
            String codigoTemporal,
            Integer idMascota,
            Integer prioridad,
            Boolean estado,
            Integer idMetodoIngreso) {
    }

    record TriajeFilterRequest() {
    }

    record TriajeDeleteRequest(
            Integer idTriaje) {
    }
}
