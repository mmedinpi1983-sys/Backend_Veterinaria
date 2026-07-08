package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

public interface MedicamentoCatalogoDTO {

    record MedicamentoCatalogoCatalogResponse(
            Integer idMedicamento,
            String codigoMedicamento,
            String nombreMedicamento) {
    }

    record MedicamentoCatalogoListResponse(
            Integer idMedicamento,
            String codigoMedicamento,
            String nombreMedicamento,
            String descripcion) {
    }

    record MedicamentoCatalogoDetailResponse(
            Integer idMedicamento,
            String codigoMedicamento,
            String nombreMedicamento,
            String descripcion,
            String concentracion,
            String presentacion) {
    }

    record MedicamentoCatalogoCreateRequest(
            String codigoMedicamento,
            String nombreMedicamento,
            String descripcion,
            String concentracion,
            String presentacion) {
    }

    record MedicamentoCatalogoUpdateRequest(
            String codigoMedicamento,
            String nombreMedicamento,
            String descripcion,
            String concentracion,
            String presentacion) {
    }

    record MedicamentoCatalogoDeleteRequest(
            Integer idMedicamento) {
    }
}
