package com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection;

import java.time.LocalDateTime;

public interface AsociadoListarProjection {
    Integer getIdAsociado();
    String getNombre();
    String getRuc();
    String getNombreDueno();
    LocalDateTime getFechaCreacion();
    Boolean getActivo();
}
