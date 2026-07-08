package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection;

import java.time.LocalDateTime;

public interface RolPermisoListarProjection {
    Integer getIdRolPermiso();
    Integer getIdRolesClinica();
    Integer getIdPermiso();
    LocalDateTime getFechaCreacion();
}
