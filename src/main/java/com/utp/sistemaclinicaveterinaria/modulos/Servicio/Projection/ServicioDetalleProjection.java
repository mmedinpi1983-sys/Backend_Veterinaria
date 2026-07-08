package com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public interface ServicioDetalleProjection {
    Integer getIdServicio();
    String getNombre();
    Integer getIdCategoria();
    BigDecimal getPrecio();
    Boolean getRequiereTriaje();
    Boolean getEstado();
    Integer getDuracionEstimada();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
