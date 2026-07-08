package com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TurnoDetalleProjection {

    String getNombre();

    LocalTime getHoraInicio();

    LocalTime getHoraFin();

    Boolean getEstado();

    String getEmpleadoCreador();

    LocalDateTime getFechaCreacion();

    String getEmpleadoModificador();

    LocalDateTime getFechaModificacion();

    String getEmpleadoEliminador();

    LocalDateTime getFechaEliminacion();
}
