package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface TriajeDetalleDTO {
    record Request(
                 BigDecimal temperatura,
                 BigDecimal peso,
                 String observaciones,
                 String alergias,
                 Integer idTriaje
    ) {}
    record Response(
                 Integer idTriajeDetalle,
                 BigDecimal temperatura,
                 BigDecimal peso,
                 String observaciones,
                 String alergias,
                 Integer idTriaje,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idTriajeDetalle,
                 String observaciones,
                 String alergias,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
