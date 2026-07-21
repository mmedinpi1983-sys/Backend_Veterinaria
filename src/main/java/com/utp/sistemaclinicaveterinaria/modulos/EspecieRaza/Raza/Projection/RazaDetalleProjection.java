package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.Projection;

import java.time.LocalDateTime;

public interface RazaDetalleProjection {
    Integer getIdEspecieRaza();
    String getNombre();
    Integer getIdEspecie();
    String getNombreEspecie();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
