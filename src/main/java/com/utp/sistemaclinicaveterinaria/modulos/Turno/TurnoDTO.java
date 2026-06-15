package com.utp.sistemaclinicaveterinaria.modulos.Turno;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface TurnoDTO {
    record Request(
                 String nombre,
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 Boolean estado,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idTurno,
                 String nombre,
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Boolean estado,
                 Integer idAsociado,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idTurno,
                 String nombre,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
