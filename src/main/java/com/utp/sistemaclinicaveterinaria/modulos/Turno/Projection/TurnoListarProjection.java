package com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TurnoListarProjection {
    Integer getIdTurno();

    String getNombre();

    LocalTime getHoraInicio();

    LocalTime getHoraFin();

    LocalDateTime getFechaCreacion();

    Boolean getEstado();

}