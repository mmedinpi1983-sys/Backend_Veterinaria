package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

public interface EstadoSalidaAtencionDTO {

    record EstadoSalidaAtencionCatalogResponse(
            Integer idEstadoSalida,
            String nombre) {
    }

    record EstadoSalidaAtencionListResponse(
            Integer idEstadoSalida,
            String nombre,
            String descripcion) {
    }

    record EstadoSalidaAtencionDetailResponse(
            Integer idEstadoSalida,
            String nombre,
            String descripcion) {
    }

    record EstadoSalidaAtencionCreateRequest(
            String nombre,
            String descripcion) {
    }

    record EstadoSalidaAtencionUpdateRequest(
            String nombre,
            String descripcion) {
    }

    record EstadoSalidaAtencionFilterRequest(
            String nombre) {
    }

    record EstadoSalidaAtencionDeleteRequest(
            Integer idEstadoSalida) {
    }
}
