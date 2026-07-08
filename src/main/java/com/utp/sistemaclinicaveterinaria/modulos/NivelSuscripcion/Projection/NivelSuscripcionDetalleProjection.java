package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public interface NivelSuscripcionDetalleProjection {
    Integer getIdNivel();
    String getNombre();
    Integer getCantidadUsuario();
    BigDecimal getPrecioMensual();
    BigDecimal getPrecioAnual();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
