package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface PresentacionDTO {
    record Request(
                 String nombre,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idPresentacion,
                 String nombre,
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
                 Integer idPresentacion,
                 String nombre,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
