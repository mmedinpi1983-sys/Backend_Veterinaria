package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection;

import java.time.LocalDateTime;

public interface AtencionEsteticaListarProjection {
    Integer getIdEstetica();
    String getDetalleServicio();
    String getObservaciones();
    LocalDateTime getFechaCreacion();
    Boolean getEstado();
}
