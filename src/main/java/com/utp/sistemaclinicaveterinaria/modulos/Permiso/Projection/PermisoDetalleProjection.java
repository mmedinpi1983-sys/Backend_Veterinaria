package com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection;

import java.time.LocalDateTime;

public interface PermisoDetalleProjection {
    Integer getIdPermiso();
    String getNombrePermiso();
    String getDescripcionPermiso();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
    LocalDateTime getFechaModificacion();
    LocalDateTime getFechaEliminacion();
}
