package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import java.util.List;

public interface DocumentoIdentidadDTO {

    record DocumentoIdentidadCatalogResponse(
            Integer idDocumentoIdentidad,
            String descripcion) {
    }

    record DocumentoIdentidadListResponse(
            Integer idDocumentoIdentidad,
            String descripcion,
            Boolean estado) {
    }

    record DocumentoIdentidadDetailResponse(
            Integer idDocumentoIdentidad,
            String descripcion,
            Boolean estado) {
    }

    record DocumentoIdentidadCreateRequest(
            String descripcion,
            Boolean estado) {
    }

    record DocumentoIdentidadUpdateRequest(
            String descripcion,
            Boolean estado) {
    }

    record DocumentoIdentidadFilterRequest(
            String descripcion,
            Boolean estado) {
    }

    record DocumentoIdentidadDeleteRequest(
            Integer idDocumentoIdentidad) {
    }
}
