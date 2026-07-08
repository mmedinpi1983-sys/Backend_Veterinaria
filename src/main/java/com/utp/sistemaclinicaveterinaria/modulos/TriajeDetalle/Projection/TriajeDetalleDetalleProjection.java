package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TriajeDetalleDetalleProjection {
    Integer getIdTriajeDetalle();
    BigDecimal getTemperatura();
    BigDecimal getPeso();
    String getObservaciones();
    String getAlergias();
    Integer getIdTriaje();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
