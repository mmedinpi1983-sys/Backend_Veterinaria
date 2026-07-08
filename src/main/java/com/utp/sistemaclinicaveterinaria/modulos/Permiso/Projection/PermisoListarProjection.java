package com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection;

import java.time.LocalDateTime;

public interface PermisoListarProjection {
    Integer getIdPermiso();
    String getNombrePermiso();
    String getDescripcionPermiso();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
