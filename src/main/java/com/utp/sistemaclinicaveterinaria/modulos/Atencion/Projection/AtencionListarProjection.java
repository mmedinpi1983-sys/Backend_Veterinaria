package com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection;

import java.time.LocalDateTime;

public interface AtencionListarProjection {
    Integer getIdAtencion();
    String getObservacion();
    LocalDateTime getFechaCreacion();
}
