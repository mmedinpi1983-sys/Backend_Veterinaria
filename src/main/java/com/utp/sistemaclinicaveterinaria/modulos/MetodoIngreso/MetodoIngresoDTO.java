package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface MetodoIngresoDTO {
    record Request(
                 String nombre,
                 String descripcion,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idMetodoIngreso,
                 String nombre,
                 String descripcion,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record ListItem(
                 Integer idMetodoIngreso,
                 String nombre,
                 String descripcion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
