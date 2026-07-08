package com.utp.sistemaclinicaveterinaria.modulos.Asociado;
import java.time.*;

public interface AsociadoDTO {

    record AsociadoListResponse(
            Integer idAsociado,
            String nombre,
            String ruc,
            String nombreDueno,
            LocalDateTime fechaCreacion,
            Boolean activo) {
    }

    record AsociadoDetailResponse(
            Integer idAsociado,
            String nombre,
            String ruc,
            String nombreDueno,
            String apellidoDueno,
            Integer idNivelSuscripcion,
            Boolean activo,
            String correoElectronico,
            String nroTelefono,
            String direccion,
            String diasAtencion,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AsociadoCreateRequest(
            String nombre,
            String ruc,
            String nombreDueno,
            String apellidoDueno,
            Integer idNivelSuscripcion,
            Boolean activo,
            String correoElectronico,
            String nroTelefono,
            String direccion,
            String diasAtencion) {
    }

    record AsociadoUpdateRequest(
            String nombre,
            String ruc,
            String nombreDueno,
            String apellidoDueno,
            Integer idNivelSuscripcion,
            Boolean activo,
            String correoElectronico,
            String nroTelefono,
            String direccion,
            String diasAtencion) {
    }

    record AsociadoFilterRequest(
            String nombre,
            Boolean activo) {
    }

    record AsociadoDeleteRequest(
            Integer idAsociado) {
    }
}
