package com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection;

import java.time.LocalDateTime;

public interface TriajeDetalleProjection {
    Integer getIdTriaje();
    Integer getIdCitaProgramada();
    String getCodigoTemporal();
    Integer getIdMascota();
    Integer getPrioridad();
    Boolean getEstado();
    Integer getIdMetodoIngreso();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
