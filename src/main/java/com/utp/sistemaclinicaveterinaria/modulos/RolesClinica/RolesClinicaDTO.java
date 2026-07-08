package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import java.time.*;

public interface RolesClinicaDTO {

    record RolesClinicaCatalogResponse(
            Integer idRoles,
            String nombreRol) {
    }

    record RolesClinicaListResponse(
            Integer idRoles,
            String nombreRol,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record RolesClinicaDetailResponse(
            Integer idRoles,
            String nombreRol,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record RolesClinicaCreateRequest(
            String nombreRol) {
    }

    record RolesClinicaUpdateRequest(
            String nombreRol,
            Boolean estado) {
    }

    record RolesClinicaFilterRequest(
            String nombreRol,
            Boolean estado) {
    }

    record RolesClinicaDeleteRequest(
            Integer idRoles) {
    }
}
