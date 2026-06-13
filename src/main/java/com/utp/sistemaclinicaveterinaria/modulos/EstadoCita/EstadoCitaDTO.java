package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface EstadoCitaDTO {
    record Request(
                 String nombre,
                 String descripcion
    ) {}
    record Response(
                 Integer idEstadoCita,
                 String nombre,
                 String descripcion
    ) {}
    record ListItem(
                 Integer idEstadoCita,
                 String nombre,
                 String descripcion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
