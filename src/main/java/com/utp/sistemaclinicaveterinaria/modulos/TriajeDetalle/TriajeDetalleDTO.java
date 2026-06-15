package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface TriajeDetalleDTO {
    record Request(
                 @DecimalMin(value = "1.0", message = "La temperatura debe ser mayor a 1°C")
                 @DecimalMax(value = "50.0", message = "La temperatura no puede superar 50°C")
                 BigDecimal temperatura,
                 @Positive(message = "El peso debe ser mayor a 0")
                 BigDecimal peso,
                 String observaciones,
                 String alergias,
                 Integer idTriaje
    ) {}
    record Response(
                 Integer idTriajeDetalle,
                 BigDecimal temperatura,
                 BigDecimal peso,
                 String observaciones,
                 String alergias,
                 Integer idTriaje,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idTriajeDetalle,
                 String observaciones,
                 String alergias,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
