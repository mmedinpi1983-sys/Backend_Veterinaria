package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection;

import java.time.LocalDateTime;

public interface RolesClinicaDetalleProjection {
    Integer getIdRoles();
    String getNombreRol();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
