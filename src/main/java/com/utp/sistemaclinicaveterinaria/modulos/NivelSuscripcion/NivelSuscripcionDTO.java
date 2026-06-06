package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface NivelSuscripcionDTO {
    record Request(
                 String nombre,
                 Integer cantidadUsuario,
                 BigDecimal precioMensual,
                 BigDecimal precioAnual,
                 Boolean estado
    ) {}
    record Response(
                 Integer idNivel,
                 String nombre,
                 Integer cantidadUsuario,
                 BigDecimal precioMensual,
                 BigDecimal precioAnual,
                 Boolean estado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaEliminacion,
                 LocalDateTime fechaModificacion,
                 Integer idSuperAdminCreador,
                 Integer idSuperAdminModificador,
                 Integer idSuperAdminEliminador
    ) {}
    record ListItem(
                 Integer idNivel,
                 String nombre,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
