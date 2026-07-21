package com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection;

import java.time.LocalDateTime;

public interface ConsultorioListarProjection {
    Integer getIdConsultorio();
    String getNombre();
    String getDescripcion();
    String getPiso();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
