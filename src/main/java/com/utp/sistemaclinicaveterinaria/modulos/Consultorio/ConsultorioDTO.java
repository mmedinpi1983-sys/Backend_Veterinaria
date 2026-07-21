package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public interface ConsultorioDTO {

    record ConsultorioCatalogResponse(
            Integer idConsultorio,
            String nombre,
            String piso) {
    }

    record ConsultorioListResponse(
            Integer idConsultorio,
            String nombre,
            String descripcion,
            String piso,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record ConsultorioDetailResponse(
            Integer idConsultorio,
            String nombre,
            String descripcion,
            String piso,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record ConsultorioCreateRequest(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            String piso) {
    }

    record ConsultorioUpdateRequest(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            String piso,
            Boolean estado) {
    }

    record ConsultorioFilterRequest(
            String nombre,
            Boolean estado) {
    }

    record ConsultorioDeleteRequest(
            Integer idConsultorio) {
    }
}
