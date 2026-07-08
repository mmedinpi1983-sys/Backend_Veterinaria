package com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection;

import java.time.LocalDateTime;

public interface ServicioListarProjection {
    Integer getIdServicio();
    String getNombre();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
