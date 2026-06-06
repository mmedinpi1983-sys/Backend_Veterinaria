package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface RolesClinicaDTO {
    record Request(
                 String nombreRol,
                 Boolean estado,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idRoles,
                 String nombreRol,
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
                 Integer idRoles,
                 String nombreRol,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
