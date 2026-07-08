package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;
import java.time.*;

public interface AtencionEsteticaDTO {

    record AtencionEsteticaListResponse(
            Integer idEstetica,
            String detalleServicio,
            String observaciones,
            LocalDateTime fechaCreacion,
            Boolean estado) {
    }

    record AtencionEsteticaDetailResponse(
            Integer idEstetica,
            Integer idAtencion,
            Integer idServicio,
            String detalleServicio,
            String observaciones,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AtencionEsteticaCreateRequest(
            Integer idAtencion,
            Integer idServicio,
            String detalleServicio,
            String observaciones,
            Boolean estado) {
    }

    record AtencionEsteticaUpdateRequest(
            Integer idAtencion,
            Integer idServicio,
            String detalleServicio,
            String observaciones,
            Boolean estado) {
    }

    record AtencionEsteticaFilterRequest(
            String detalleServicio,
            Boolean estado) {
    }

    record AtencionEsteticaDeleteRequest(
            Integer idEstetica) {
    }
}
