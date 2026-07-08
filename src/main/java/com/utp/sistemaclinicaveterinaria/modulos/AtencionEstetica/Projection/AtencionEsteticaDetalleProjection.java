package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection;

import java.time.LocalDateTime;

public interface AtencionEsteticaDetalleProjection {
    Integer getIdEstetica();
    Integer getIdAtencion();
    Integer getIdServicio();
    String getDetalleServicio();
    String getObservaciones();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
