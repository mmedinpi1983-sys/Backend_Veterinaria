package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface RolPermisoDTO {
    record Request(
                 Integer idRolesClinica,
                 Integer idPermiso,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idRolPermiso,
                 Integer idRolesClinica,
                 Integer idPermiso,
                 Integer idAsociado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idRolPermiso,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
