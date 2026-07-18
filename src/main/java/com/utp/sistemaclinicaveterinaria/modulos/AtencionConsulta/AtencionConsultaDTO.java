package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;
import java.time.*;

public interface AtencionConsultaDTO {

    record AtencionConsultaListResponse(
            Integer idConsulta,
            String evaluacionClinica,
            String tratamiento,
            String indicaciones,
            LocalDateTime fechaCreacion) {
    }

    record AtencionConsultaDetailResponse(
            Integer idConsulta,
            Integer idAtencion,
            String evaluacionClinica,
            String tratamiento,
            String indicaciones,
            String observaciones,
            Boolean requiereControl,
            LocalDate fechaProximoControl,
            String motivoConsulta,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AtencionConsultaCreateRequest(
            Integer idAtencion,
            String evaluacionClinica,
            String tratamiento,
            String indicaciones,
            String observaciones,
            Boolean requiereControl,
            LocalDate fechaProximoControl,
            String motivoConsulta) {
    }

    record AtencionConsultaCreateResponse(
            Integer idConsulta) {
    }

    record AtencionConsultaUpdateRequest(
            Integer idAtencion,
            String evaluacionClinica,
            String tratamiento,
            String indicaciones,
            String observaciones,
            Boolean requiereControl,
            LocalDate fechaProximoControl,
            String motivoConsulta) {
    }

    record AtencionConsultaFilterRequest(
            String evaluacionClinica) {
    }

    record AtencionConsultaDeleteRequest(
            Integer idConsulta) {
    }
}
