package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import java.time.*;
import java.util.List;
public interface DuenoMascotaDTO {
    record Request(
                 Integer idDueno,
                 Integer idMascota,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idDuenoMascota,
                 Integer idDueno,
                 Integer idMascota,
                 Integer idAsociado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idDuenoMascota,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
