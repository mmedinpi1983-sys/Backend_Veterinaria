package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface AtencionEsteticaDTO {
    record Request(
                 Integer idAtencion,
                 Integer idServicio,
                 String detalleServicio,
                 String observaciones,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idEstetica,
                 Integer idAtencion,
                 Integer idServicio,
                 String detalleServicio,
                 String observaciones,
                 Integer idAsociado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Boolean estado,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idEstetica,
                 String detalleServicio,
                 String observaciones,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
