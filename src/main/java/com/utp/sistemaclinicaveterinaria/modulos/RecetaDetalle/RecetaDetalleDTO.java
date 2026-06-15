package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface RecetaDetalleDTO {
    record Request(
                 Integer idReceta,
                 Integer idMedicamento,
                 @NotBlank(message = "La dosis es obligatoria")
                 @Size(max = 100, message = "La dosis no puede superar 100 caracteres")
                 String dosis,
                 @NotBlank(message = "La frecuencia es obligatoria")
                 String frecuencia,
                 @NotBlank(message = "La duración es obligatoria")
                 String duracion,
                 Integer viaAdministracion,
                 String indicacionesEspecificas
    ) {}
    record Response(
                 Integer idRecetaDetalle,
                 Integer idReceta,
                 Integer idMedicamento,
                 String dosis,
                 String frecuencia,
                 String duracion,
                 Integer viaAdministracion,
                 String indicacionesEspecificas,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idRecetaDetalle,
                 String dosis,
                 String frecuencia,
                 String duracion,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
