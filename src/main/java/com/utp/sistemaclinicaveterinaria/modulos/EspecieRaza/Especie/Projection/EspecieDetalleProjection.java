package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.Projection;

import java.time.LocalDateTime;

public interface EspecieDetalleProjection {
    Integer getIdEspecieRaza();
    String getNombre();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
