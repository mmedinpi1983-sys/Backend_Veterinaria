package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection;

import java.time.LocalDateTime;

public interface CitaProgramadaListarProjection {
    Integer getIdCitaProgramada();
    String getMotivo();
    String getMotivoReprogramacion();
    LocalDateTime getFechaCreacion();
}
