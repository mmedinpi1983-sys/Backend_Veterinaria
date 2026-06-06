package com.utp.sistemaclinicaveterinaria.modulos.Servicio;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface ServicioDTO {
    record Request(
                 String nombre,
                 Integer idCategoria,
                 Integer idAsociado,
                 BigDecimal precio,
                 Boolean requiereTriaje,
                 Boolean estado,
                 Integer duracionEstimada
    ) {}
    record Response(
                 Integer idServicio,
                 String nombre,
                 Integer idCategoria,
                 Integer idAsociado,
                 BigDecimal precio,
                 Boolean requiereTriaje,
                 Boolean estado,
                 Integer duracionEstimada,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idServicio,
                 String nombre,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
