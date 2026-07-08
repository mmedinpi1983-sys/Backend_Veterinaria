package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import java.time.*;

public interface NivelPermisoDTO {

    record NivelPermisoCatalogResponse(
            Integer idNivelPermiso,
            Integer idPermiso,
            Integer idNivelSuscripcion,
            Boolean estado) {
    }

    record NivelPermisoListResponse(
            Integer idNivelPermiso,
            Integer idPermiso,
            Integer idNivelSuscripcion,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record NivelPermisoDetailResponse(
            Integer idNivelPermiso,
            Integer idPermiso,
            Integer idNivelSuscripcion,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record NivelPermisoCreateRequest(
            Integer idPermiso,
            Integer idNivelSuscripcion,
            Boolean estado) {
    }

    record NivelPermisoUpdateRequest(
            Integer idPermiso,
            Integer idNivelSuscripcion,
            Boolean estado) {
    }

    record NivelPermisoDeleteRequest(
            Integer idNivelPermiso) {
    }
}
