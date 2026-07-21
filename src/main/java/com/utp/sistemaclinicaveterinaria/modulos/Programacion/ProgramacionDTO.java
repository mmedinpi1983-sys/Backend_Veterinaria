package com.utp.sistemaclinicaveterinaria.modulos.Programacion;
import java.time.*;

public interface ProgramacionDTO {
    record ProgramacionCatalogResponse(Integer idProgramacion, String ambiente, String descripcion) {}
    record ProgramacionListResponse(
            Integer idProgramacion, LocalDate fecha,
            String nombreVeterinario, String nombreTurno, String horaInicio, String horaFin,
            String nombreEstadoProgramacion,
            String ambiente, String descripcion, LocalDateTime fechaCreacion) {}
    record ProgramacionFilterRequest(String fecha, Integer idEmpleadoRegistrador,
            Integer idEstadoProgramacion, Integer idTurno) {}
    record ProgramacionDetailResponse(
            Integer idProgramacion, LocalDate fecha, Integer idTurno, Integer idEmpleadoRegistrador,
            Integer idEstadoProgramacion, Integer idCategoria, Integer idServicio,
            Integer idConsultorio, String ambiente, String descripcion,
            String usuarioCreador, LocalDateTime fechaCreacion,
            String usuarioModificador, LocalDateTime fechaModificacion,
            String usuarioEliminador, LocalDateTime fechaEliminacion) {}
    record ProgramacionCreateRequest(LocalDate fecha, Integer idTurno, Integer idEmpleadoRegistrador,
            Integer idEstadoProgramacion, Integer idCategoria, Integer idServicio,
            Integer idConsultorio, String ambiente, String descripcion) {}
    record ProgramacionUpdateRequest(LocalDate fecha, Integer idTurno, Integer idEmpleadoRegistrador,
            Integer idEstadoProgramacion, Integer idCategoria, Integer idServicio,
            Integer idConsultorio, String ambiente, String descripcion) {}
    record ProgramacionDeleteRequest(Integer idProgramacion) {}
}
