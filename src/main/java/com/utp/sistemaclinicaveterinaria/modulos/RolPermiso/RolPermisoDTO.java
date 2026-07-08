package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import java.time.*;

public interface RolPermisoDTO {

    record RolPermisoCatalogResponse(
            Integer idRolPermiso,
            Integer idRolesClinica,
            Integer idPermiso) {
    }

    record RolPermisoListResponse(
            Integer idRolPermiso,
            Integer idRolesClinica,
            Integer idPermiso,
            LocalDateTime fechaCreacion) {
    }

    record RolPermisoDetailResponse(
            Integer idRolPermiso,
            Integer idRolesClinica,
            Integer idPermiso,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record RolPermisoCreateRequest(
            Integer idRolesClinica,
            Integer idPermiso) {
    }

    record RolPermisoUpdateRequest(
            Integer idRolesClinica,
            Integer idPermiso) {
    }

    record RolPermisoFilterRequest() {
    }

    record RolPermisoDeleteRequest(
            Integer idRolPermiso) {
    }
}
