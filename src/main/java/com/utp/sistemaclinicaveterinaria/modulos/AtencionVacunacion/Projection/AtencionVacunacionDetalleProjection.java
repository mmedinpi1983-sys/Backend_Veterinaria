package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AtencionVacunacionDetalleProjection {
    Integer getIdVacunacion();
    Integer getIdAtencion();
    Integer getIdVacuna();
    Integer getDosis();
    LocalDate getFechaAplicacion();
    LocalDate getFechaRefuerzo();
    String getObservaciones();
    Boolean getEstado();
    Integer getIdConsulta();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
