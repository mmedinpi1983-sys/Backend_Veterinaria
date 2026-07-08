package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

public interface EstadoAtencionDTO {

    record EstadoAtencionCatalogResponse(
            Integer idEstadoAtencion,
            String nombre) {
    }

    record EstadoAtencionListResponse(
            Integer idEstadoAtencion,
            String nombre) {
    }

    record EstadoAtencionDetailResponse(
            Integer idEstadoAtencion,
            String nombre) {
    }

    record EstadoAtencionCreateRequest(
            String nombre) {
    }

    record EstadoAtencionUpdateRequest(
            String nombre) {
    }

    record EstadoAtencionFilterRequest(
            String nombre) {
    }

    record EstadoAtencionDeleteRequest(
            Integer idEstadoAtencion) {
    }
}
