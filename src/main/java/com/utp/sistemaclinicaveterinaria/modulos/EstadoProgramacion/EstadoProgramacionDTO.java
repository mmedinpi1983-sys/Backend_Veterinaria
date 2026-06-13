package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface EstadoProgramacionDTO {
    record Request(
                 String nombre,
                 String descripcion,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idEstadoProgramacion,
                 String nombre,
                 String descripcion,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record ListItem(
                 Integer idEstadoProgramacion,
                 String nombre,
                 String descripcion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
