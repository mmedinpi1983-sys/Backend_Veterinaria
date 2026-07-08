package com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface AtencionDetalleProjection {
    Integer getIdAtencion();
    Integer getIdCitaProgramada();
    Integer getIdTriaje();
    LocalDate getFechaAtencion();
    LocalTime getHoraInicio();
    LocalTime getHoraFin();
    String getObservacion();
    Integer getIdEstadoSalida();
    Integer getIdEstadoAtencion();
    Integer getIdMascota();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
