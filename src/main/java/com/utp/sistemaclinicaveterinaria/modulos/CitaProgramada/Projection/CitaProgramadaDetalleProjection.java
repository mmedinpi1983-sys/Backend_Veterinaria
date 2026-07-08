package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CitaProgramadaDetalleProjection {
    Integer getIdCitaProgramada();
    Integer getIdDueno();
    Integer getIdProgramacion();
    Integer getIdMascota();
    LocalDate getFecha();
    LocalTime getHoraInicio();
    LocalTime getHoraFin();
    Integer getIdEstadoCita();
    String getMotivo();
    Integer getIdCategoria();
    Integer getIdServicio();
    String getMotivoReprogramacion();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
