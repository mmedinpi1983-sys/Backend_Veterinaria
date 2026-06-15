package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface CitaProgramadaDTO {
    record Request(
                 @NotNull(message = "El dueño es obligatorio")
                 Integer idDueno,
                 Integer idProgramacion,
                 @NotNull(message = "La mascota es obligatoria")
                 Integer idMascota,
                 @NotNull(message = "La fecha es obligatoria")
                 @FutureOrPresent(message = "La fecha de la cita no puede ser en el pasado")
                 LocalDate fecha,
                 @NotNull(message = "La hora de inicio es obligatoria")
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 Integer idEstadoCita,
                 @Size(max = 255, message = "El motivo no puede superar 255 caracteres")
                 String motivo,
                 Integer idAsociado,
                 Integer idCategoria,
                 Integer idServicio,
                 String motivoReprogramacion
    ) {}
    record Response(
                 Integer idCitaProgramada,
                 Integer idDueno,
                 Integer idProgramacion,
                 Integer idMascota,
                 LocalDate fecha,
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 Integer idEstadoCita,
                 String motivo,
                 Integer idAsociado,
                 Integer idCategoria,
                 Integer idServicio,
                 String motivoReprogramacion,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idCitaProgramada,
                 String motivo,
                 String motivoReprogramacion,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}

    record CitaEnriquecida(
        Integer idCitaProgramada,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        String nombreMascota,
        String especie,
        String raza,
        String nombreDueno,
        String nombreServicio,
        String nombreVeterinario,
        String estadoCita,
        Integer idEstadoCita,
        String motivo
    ) {}

    record StatsResponse(
        long total,
        long pendientes,
        long completadas,
        long canceladas
    ) {}

    // Veterinario disponible para el select del modal de Nueva Cita.
    // idProgramacion: se manda al crear la cita (enlaza la cita con el veterinario y su turno).
    // horaInicio/horaFin: rango del turno (HH:mm) para generar las horas seleccionables.
    record VeterinarioDisponible(
        Integer idProgramacion,
        String nombreVeterinario,
        String nombreTurno,
        String horaInicio,
        String horaFin
    ) {}
}