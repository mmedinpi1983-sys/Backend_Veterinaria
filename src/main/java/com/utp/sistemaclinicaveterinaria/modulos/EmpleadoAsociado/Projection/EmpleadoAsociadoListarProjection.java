package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection;

import java.time.LocalDateTime;

public interface EmpleadoAsociadoListarProjection {
    Integer getIdEmpleadoAsociado();
    String getCorreo();
    String getNombreEmpleado();
    String getApellidoPaterno();
    String getApellidoMaterno();
    String getNroDocumento();
    Integer getIdRolesClinica();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
