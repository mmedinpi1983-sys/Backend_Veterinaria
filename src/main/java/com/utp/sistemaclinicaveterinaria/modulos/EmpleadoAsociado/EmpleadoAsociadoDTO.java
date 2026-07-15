package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;
import java.time.*;
import jakarta.validation.constraints.*;
public interface EmpleadoAsociadoDTO {

    record EmpleadoAsociadoCatalogResponse(
            Integer idEmpleadoAsociado,
            String correo,
            String nombreEmpleado,
            String apellidoPaterno,
            String apellidoMaterno) {
    }

    record EmpleadoAsociadoListResponse(
            Integer idEmpleadoAsociado,
            String correo,
            String nombreEmpleado,
            String apellidoPaterno,
            String apellidoMaterno,
            String nroDocumento,
            Integer idRolesClinica,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record EmpleadoAsociadoDetailResponse(
            Integer idEmpleadoAsociado,
            Integer idAsociado,
            String correo,
            String nombreEmpleado,
            String apellidoPaterno,
            String apellidoMaterno,
            String nroDocumento,
            LocalDateTime fechaNacimiento,
            Integer idDocumentoIdentidad,
            Integer idRolesClinica,
            String correoElectronico,
            String nroTelefono,
            Boolean estado,
            String empleadoCreador,
            LocalDateTime fechaCreacion,
            String empleadoModificador,
            LocalDateTime fechaModificacion,
            String empleadoEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record EmpleadoAsociadoCreateRequest(
            String correo,
            @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
            String contrasena,
            @NotBlank(message = "El nombre es obligatorio")
            String nombreEmpleado,
            @NotBlank(message = "El apellido paterno es obligatorio")
            String apellidoPaterno,
            @NotBlank(message = "El apellido materno es obligatorio")
            String apellidoMaterno,
            @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener 8 dígitos")
            String nroDocumento,
            LocalDateTime fechaNacimiento,
            Integer idDocumentoIdentidad,
            Integer idRolesClinica,
            String correoElectronico,
            String nroTelefono) {
    }

    record EmpleadoAsociadoUpdateRequest(
            String correo,
            String contrasena,
            String nombreEmpleado,
            String apellidoPaterno,
            String apellidoMaterno,
            String nroDocumento,
            LocalDateTime fechaNacimiento,
            Integer idDocumentoIdentidad,
            Integer idRolesClinica,
            String correoElectronico,
            String nroTelefono,
            Boolean estado) {
    }

    record EmpleadoAsociadoFilterRequest(
            String q,
            Boolean estado) {
    }

    record EmpleadoAsociadoDeleteRequest(
            Integer idEmpleadoAsociado) {
    }
}
