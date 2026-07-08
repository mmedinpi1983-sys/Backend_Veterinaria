package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

public interface EstadoCitaDTO {

    record EstadoCitaCatalogResponse(
            Integer idEstadoCita,
            String nombre) {
    }

    record EstadoCitaListResponse(
            Integer idEstadoCita,
            String nombre,
            String descripcion) {
    }

    record EstadoCitaDetailResponse(
            Integer idEstadoCita,
            String nombre,
            String descripcion) {
    }

    record EstadoCitaCreateRequest(
            String nombre,
            String descripcion) {
    }

    record EstadoCitaUpdateRequest(
            String nombre,
            String descripcion) {
    }

    record EstadoCitaFilterRequest(
            String nombre) {
    }

    record EstadoCitaDeleteRequest(
            Integer idEstadoCita) {
    }
}
