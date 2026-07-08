package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection;

import java.time.LocalDateTime;

public interface RecetaDetalleListarProjection {
    Integer getIdRecetaDetalle();
    String getDosis();
    String getFrecuencia();
    String getDuracion();
    LocalDateTime getFechaCreacion();
}
