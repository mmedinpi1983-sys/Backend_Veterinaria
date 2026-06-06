package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface EstadoAtencionDTO {
    record Request(
                 String nombre
    ) {}
    record Response(
                 Integer idEstadoAtencion,
                 String nombre
    ) {}
    record ListItem(
                 Integer idEstadoAtencion,
                 String nombre
    ) {}
    record ListResponse(List<ListItem> items) {}
}
