package com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection;

import java.time.LocalDateTime;

public interface PresentacionDetalleProjection {
    Integer getIdPresentacion();
    String getNombre();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
