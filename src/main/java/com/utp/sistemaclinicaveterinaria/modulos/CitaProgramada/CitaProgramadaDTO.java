package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import java.time.*;
import java.util.List;
import jakarta.validation.constraints.*;

public interface CitaProgramadaDTO {

    record CitaProgramadaListResponse(
            Integer idCitaProgramada,
            String motivo,
            String motivoReprogramacion,
            LocalDateTime fechaCreacion) {
    }

    record CitaProgramadaDetailResponse(
            Integer idCitaProgramada,
            Integer idDueno,
            Integer idProgramacion,
            Integer idMascota,
            LocalDate fecha,
            LocalTime horaInicio,
            LocalTime horaFin,
            Integer idEstadoCita,
            String motivo,
            Integer idCategoria,
            Integer idServicio,
            String motivoReprogramacion,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record CitaProgramadaCreateRequest(
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
            Integer idCategoria,
            Integer idServicio,
            String motivoReprogramacion) {
    }

    record CitaProgramadaUpdateRequest(
            Integer idDueno,
            Integer idProgramacion,
            Integer idMascota,
            LocalDate fecha,
            LocalTime horaInicio,
            LocalTime horaFin,
            Integer idEstadoCita,
            String motivo,
            Integer idCategoria,
            Integer idServicio,
            String motivoReprogramacion) {
    }

    record CitaProgramadaFilterRequest(
            String motivo) {
    }

    record CitaProgramadaDeleteRequest(
            Integer idCitaProgramada) {
    }

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
        String motivo,
        Integer idAtencion
    ) {}

    record StatsResponse(
        long total,
        long pendientes,
        long completadas,
        long canceladas
    ) {}

    record VeterinarioDisponible(
        Integer idProgramacion,
        String nombreVeterinario,
        String nombreTurno,
        String horaInicio,
        String horaFin
    ) {}
}
