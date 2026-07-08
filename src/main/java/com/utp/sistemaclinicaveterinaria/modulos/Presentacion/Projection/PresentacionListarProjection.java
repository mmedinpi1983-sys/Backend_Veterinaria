package com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection;

import java.time.LocalDateTime;

public interface PresentacionListarProjection {
    Integer getIdPresentacion();
    String getNombre();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
