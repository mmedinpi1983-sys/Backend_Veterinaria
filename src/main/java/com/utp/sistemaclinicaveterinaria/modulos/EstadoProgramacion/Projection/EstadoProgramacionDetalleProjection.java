package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection;

import java.time.LocalDateTime;

public interface EstadoProgramacionDetalleProjection {
    Integer getIdEstadoProgramacion();
    String getNombre();
    String getDescripcion();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
