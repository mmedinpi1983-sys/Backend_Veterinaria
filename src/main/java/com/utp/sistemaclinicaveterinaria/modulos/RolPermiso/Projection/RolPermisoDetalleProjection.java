package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection;

import java.time.LocalDateTime;

public interface RolPermisoDetalleProjection {
    Integer getIdRolPermiso();
    Integer getIdRolesClinica();
    Integer getIdPermiso();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
