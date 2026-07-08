package com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection;

import java.time.LocalDateTime;

public interface DuenoListarProjection {
    Integer getIdDueno();
    String getNombre();
    String getApellidoPaterno();
    String getApellidoMaterno();
    String getNroDocumento();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
