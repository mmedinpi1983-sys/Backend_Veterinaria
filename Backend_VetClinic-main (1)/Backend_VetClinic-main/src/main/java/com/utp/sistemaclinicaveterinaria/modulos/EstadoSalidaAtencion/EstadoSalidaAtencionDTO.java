package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface EstadoSalidaAtencionDTO {
    record Request(
                 String nombre,
                 String descripcion
    ) {}
    record Response(
                 Integer idEstadoSalida,
                 String nombre,
                 String descripcion
    ) {}
    record ListItem(
                 Integer idEstadoSalida,
                 String nombre,
                 String descripcion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
