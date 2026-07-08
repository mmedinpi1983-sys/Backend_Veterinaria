package com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection;

import java.time.LocalDateTime;

public interface RecetaListarProjection {
    Integer getIdReceta();
    LocalDateTime getFechaReceta();
    LocalDateTime getFechaCreacion();
}
