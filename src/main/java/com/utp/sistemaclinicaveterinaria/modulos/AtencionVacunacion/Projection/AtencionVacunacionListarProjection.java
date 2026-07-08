package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection;

import java.time.LocalDateTime;

public interface AtencionVacunacionListarProjection {
    Integer getIdVacunacion();
    String getObservaciones();
    LocalDateTime getFechaCreacion();
    Boolean getEstado();
}
