package com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection;

import java.time.LocalDateTime;

public interface RecetaCatalogoProjection {
    Integer getIdReceta();
    LocalDateTime getFechaReceta();
}
