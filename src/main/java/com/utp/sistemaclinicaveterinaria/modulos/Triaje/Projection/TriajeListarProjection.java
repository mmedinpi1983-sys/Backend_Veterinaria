package com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection;

import java.time.LocalDateTime;

public interface TriajeListarProjection {
    Integer getIdTriaje();
    String getCodigoTemporal();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
