package com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection;

import java.time.LocalDateTime;

public interface RecetaDetalleProjection {
    Integer getIdReceta();
    Integer getIdConsulta();
    LocalDateTime getFechaReceta();
    Integer getIdEmpleadoAsociado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
