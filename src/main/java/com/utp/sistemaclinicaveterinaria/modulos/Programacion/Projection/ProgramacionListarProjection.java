package com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection;

import java.time.LocalDateTime;

public interface ProgramacionListarProjection {
    Integer getIdProgramacion();
    String getAmbiente();
    String getDescripcion();
    LocalDateTime getFechaCreacion();
}
