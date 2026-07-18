package com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProgramacionListarProjection {
    Integer getIdProgramacion();
    LocalDate getFecha();
    String getNombreVeterinario();
    String getNombreTurno();
    String getHoraInicio();
    String getHoraFin();
    String getNombreEstadoProgramacion();
    String getAmbiente();
    String getDescripcion();
    LocalDateTime getFechaCreacion();
}
