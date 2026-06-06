package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface PermisoDTO {
    record Request(
                 String nombrePermiso,
                 String descripcionPermiso,
                 Integer idAsociado,
                 Boolean estado
    ) {}
    record Response(
                 Integer idPermiso,
                 String nombrePermiso,
                 String descripcionPermiso,
                 Integer idAsociado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Boolean estado
    ) {}
    record ListItem(
                 Integer idPermiso,
                 String nombrePermiso,
                 String descripcionPermiso,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
