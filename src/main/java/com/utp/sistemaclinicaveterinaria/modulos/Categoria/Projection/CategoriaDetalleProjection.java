package com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection;

import java.time.LocalDateTime;

public interface CategoriaDetalleProjection {
    Integer getIdCategoria();
    String getNombreCategoria();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
