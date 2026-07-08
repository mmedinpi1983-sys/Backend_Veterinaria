package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;
import java.time.*;

public interface AtencionVacunacionDTO {

    record AtencionVacunacionListResponse(
            Integer idVacunacion,
            String observaciones,
            LocalDateTime fechaCreacion,
            Boolean estado) {
    }

    record AtencionVacunacionDetailResponse(
            Integer idVacunacion,
            Integer idAtencion,
            Integer idVacuna,
            Integer dosis,
            LocalDate fechaAplicacion,
            LocalDate fechaRefuerzo,
            String observaciones,
            Boolean estado,
            Integer idConsulta,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AtencionVacunacionCreateRequest(
            Integer idAtencion,
            Integer idVacuna,
            Integer dosis,
            LocalDate fechaAplicacion,
            LocalDate fechaRefuerzo,
            String observaciones,
            Boolean estado,
            Integer idConsulta) {
    }

    record AtencionVacunacionUpdateRequest(
            Integer idAtencion,
            Integer idVacuna,
            Integer dosis,
            LocalDate fechaAplicacion,
            LocalDate fechaRefuerzo,
            String observaciones,
            Boolean estado,
            Integer idConsulta) {
    }

    record AtencionVacunacionFilterRequest(
            String observaciones,
            Boolean estado) {
    }

    record AtencionVacunacionDeleteRequest(
            Integer idVacunacion) {
    }
}
