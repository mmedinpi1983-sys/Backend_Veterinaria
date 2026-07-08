package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

public interface MetodoIngresoDTO {

    record MetodoIngresoCatalogResponse(
            Integer idMetodoIngreso,
            String nombre) {
    }

    record MetodoIngresoListResponse(
            Integer idMetodoIngreso,
            String nombre,
            String descripcion,
            Boolean estado) {
    }

    record MetodoIngresoDetailResponse(
            Integer idMetodoIngreso,
            String nombre,
            String descripcion,
            Boolean estado) {
    }

    record MetodoIngresoCreateRequest(
            String nombre,
            String descripcion,
            Boolean estado) {
    }

    record MetodoIngresoUpdateRequest(
            String nombre,
            String descripcion,
            Boolean estado) {
    }

    record MetodoIngresoDeleteRequest(
            Integer idMetodoIngreso) {
    }
}
