package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface TriajeDTO {
    record Request(
                 Integer idCitaProgramada,
                 String codigoTemporal,
                 Integer idMascota,
                 Integer prioridad,
                 Boolean estado,
                 Integer idAsociado,
                 Integer idMetodoIngreso
    ) {}
    record Response(
                 Integer idTriaje,
                 Integer idCitaProgramada,
                 String codigoTemporal,
                 Integer idMascota,
                 Integer prioridad,
                 Boolean estado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idAsociado,
                 Integer idMetodoIngreso,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idTriaje,
                 String codigoTemporal,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
