package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection;

import java.time.LocalDateTime;

public interface NivelPermisoDetalleProjection {
    Integer getIdNivelPermiso();
    Integer getIdPermiso();
    Integer getIdNivelSuscripcion();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
