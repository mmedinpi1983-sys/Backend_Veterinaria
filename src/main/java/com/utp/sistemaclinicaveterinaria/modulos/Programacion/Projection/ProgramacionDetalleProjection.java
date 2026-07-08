package com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProgramacionDetalleProjection {
    Integer getIdProgramacion();
    LocalDate getFecha();
    Integer getIdTurno();
    Integer getIdEmpleadoRegistrador();
    Integer getIdEstadoProgramacion();
    Integer getIdCategoria();
    Integer getIdServicio();
    String getAmbiente();
    String getDescripcion();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
