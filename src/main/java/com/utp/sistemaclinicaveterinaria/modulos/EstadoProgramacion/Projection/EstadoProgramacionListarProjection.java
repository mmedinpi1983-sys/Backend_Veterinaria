package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection;

import java.time.LocalDateTime;

public interface EstadoProgramacionListarProjection {
    Integer getIdEstadoProgramacion();
    String getNombre();
    String getDescripcion();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
