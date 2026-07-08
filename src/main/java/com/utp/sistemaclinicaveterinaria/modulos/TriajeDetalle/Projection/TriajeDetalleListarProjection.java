package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection;

import java.time.LocalDateTime;

public interface TriajeDetalleListarProjection {
    Integer getIdTriajeDetalle();
    String getObservaciones();
    String getAlergias();
    LocalDateTime getFechaCreacion();
}
