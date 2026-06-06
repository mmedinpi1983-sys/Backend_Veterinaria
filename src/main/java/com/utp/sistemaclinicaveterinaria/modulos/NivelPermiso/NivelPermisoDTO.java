package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface NivelPermisoDTO {
    record Request(
                 Integer idPermiso,
                 Integer idNivelSuscripcion,
                 Boolean estado
    ) {}
    record Response(
                 Integer idNivelPermiso,
                 Integer idPermiso,
                 Integer idNivelSuscripcion,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Boolean estado,
                 Integer idSuperAdminCreador,
                 Integer idSuperAdminModificador,
                 Integer idSuperAdminEliminador
    ) {}
    record ListItem(
                 Integer idNivelPermiso,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
