package com.utp.sistemaclinicaveterinaria.modulos.Receta;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface RecetaDTO {
    record Request(
                 Integer idConsulta,
                 LocalDateTime fechaReceta,
                 Integer idEmpleadoAsociado,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idReceta,
                 Integer idConsulta,
                 LocalDateTime fechaReceta,
                 Integer idEmpleadoAsociado,
                 Integer idAsociado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idReceta,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
