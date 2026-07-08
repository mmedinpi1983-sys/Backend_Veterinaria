package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection;

import java.time.LocalDateTime;

public interface EmpleadoAsociadoDetalleProjection {
    Integer getIdEmpleadoAsociado();
    Integer getIdAsociado();
    String getCorreo();
    String getNombreEmpleado();
    String getApellidoPaterno();
    String getApellidoMaterno();
    String getNroDocumento();
    LocalDateTime getFechaNacimiento();
    Integer getIdDocumentoIdentidad();
    Integer getIdRolesClinica();
    String getCorreoElectronico();
    String getNroTelefono();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
