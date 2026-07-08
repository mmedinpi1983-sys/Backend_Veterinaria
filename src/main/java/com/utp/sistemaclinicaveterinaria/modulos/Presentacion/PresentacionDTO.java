package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import java.time.*;

public interface PresentacionDTO {

    record PresentacionCatalogResponse(
            Integer idPresentacion,
            String nombre) {
    }

    record PresentacionListResponse(
            Integer idPresentacion,
            String nombre,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record PresentacionDetailResponse(
            Integer idPresentacion,
            String nombre,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record PresentacionCreateRequest(
            String nombre) {
    }

    record PresentacionUpdateRequest(
            String nombre,
            Boolean estado) {
    }

    record PresentacionFilterRequest(
            String nombre,
            Boolean estado) {
    }

    record PresentacionDeleteRequest(
            Integer idPresentacion) {
    }
}
