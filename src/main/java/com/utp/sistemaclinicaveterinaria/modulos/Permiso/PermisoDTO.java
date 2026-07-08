package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import java.time.*;

public interface PermisoDTO {

    record PermisoCatalogResponse(
            Integer idPermiso,
            String nombrePermiso) {
    }

    record PermisoListResponse(
            Integer idPermiso,
            String nombrePermiso,
            String descripcionPermiso,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record PermisoDetailResponse(
            Integer idPermiso,
            String nombrePermiso,
            String descripcionPermiso,
            Boolean estado,
            LocalDateTime fechaCreacion,
            LocalDateTime fechaModificacion,
            LocalDateTime fechaEliminacion) {
    }

    record PermisoCreateRequest(
            String nombrePermiso,
            String descripcionPermiso) {
    }

    record PermisoUpdateRequest(
            String nombrePermiso,
            String descripcionPermiso,
            Boolean estado) {
    }

    record PermisoFilterRequest(
            String nombrePermiso,
            String descripcionPermiso,
            Boolean estado) {
    }

    record PermisoDeleteRequest(
            Integer idPermiso) {
    }
}
