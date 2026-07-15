package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

public interface DiagnosticoCatalogoDTO {

    record DiagnosticoCatalogoCatalogResponse(
            Integer idDiagnosticoCatalogo,
            String codigoDiagnostico,
            String nombreDiagnostico) {
    }

    record DiagnosticoCatalogoListResponse(
            Integer idDiagnosticoCatalogo,
            String codigoDiagnostico,
            String nombreDiagnostico,
            String descripcion) {
    }

    record DiagnosticoCatalogoDetailResponse(
            Integer idDiagnosticoCatalogo,
            String codigoDiagnostico,
            String nombreDiagnostico,
            String descripcion) {
    }

    record DiagnosticoCatalogoCreateRequest(
            String codigoDiagnostico,
            String nombreDiagnostico,
            String descripcion) {
    }

    record DiagnosticoCatalogoUpdateRequest(
            String codigoDiagnostico,
            String nombreDiagnostico,
            String descripcion) {
    }

    record DiagnosticoCatalogoDeleteRequest(
            Integer idDiagnosticoCatalogo) {
    }
}
