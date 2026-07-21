package com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection;

import java.time.LocalDateTime;

public interface ConsultorioDetalleProjection {
    Integer getIdConsultorio();
    String getNombre();
    String getDescripcion();
    String getPiso();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
