package com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection;

import java.time.LocalDateTime;

public interface CategoriaListarProjection {
    Integer getIdCategoria();
    String getNombreCategoria();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
