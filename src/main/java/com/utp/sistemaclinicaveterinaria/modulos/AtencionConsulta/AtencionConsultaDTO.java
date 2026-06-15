package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface AtencionConsultaDTO {
    record Request(
                 Integer idAtencion,
                 String evaluacionClinica,
                 String tratamiento,
                 String indicaciones,
                 String observaciones,
                 Boolean requiereControl,
                 @FutureOrPresent(message = "La fecha de próximo control no puede ser en el pasado")
                 LocalDate fechaProximoControl,
                 @Size(max = 500, message = "El motivo no puede superar 500 caracteres")
                 String motivoConsulta
    ) {}
    record Response(
                 Integer idConsulta,
                 Integer idAtencion,
                 String evaluacionClinica,
                 String tratamiento,
                 String indicaciones,
                 String observaciones,
                 Boolean requiereControl,
                 LocalDate fechaProximoControl,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 String motivoConsulta,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idConsulta,
                 String evaluacionClinica,
                 String tratamiento,
                 String indicaciones,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
