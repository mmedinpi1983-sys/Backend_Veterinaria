package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface CategoriaDTO {
    record Request(
                 String nombreCategoria,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idCategoria,
                 String nombreCategoria,
                 Integer idAsociado,
                 Boolean estado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idCategoria,
                 String nombreCategoria,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
