package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public interface NivelSuscripcionListarProjection {
    Integer getIdNivel();
    String getNombre();
    Integer getCantidadUsuario();
    BigDecimal getPrecioMensual();
    BigDecimal getPrecioAnual();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
