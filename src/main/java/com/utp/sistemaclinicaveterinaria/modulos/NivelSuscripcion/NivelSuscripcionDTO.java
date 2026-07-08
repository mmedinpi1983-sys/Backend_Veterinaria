package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import java.time.*;
import java.math.BigDecimal;

public interface NivelSuscripcionDTO {

    record NivelSuscripcionCatalogResponse(
            Integer idNivel,
            String nombre) {
    }

    record NivelSuscripcionListResponse(
            Integer idNivel,
            String nombre,
            Integer cantidadUsuario,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record NivelSuscripcionDetailResponse(
            Integer idNivel,
            String nombre,
            Integer cantidadUsuario,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record NivelSuscripcionCreateRequest(
            String nombre,
            Integer cantidadUsuario,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            Boolean estado) {
    }

    record NivelSuscripcionUpdateRequest(
            String nombre,
            Integer cantidadUsuario,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            Boolean estado) {
    }

    record NivelSuscripcionDeleteRequest(
            Integer idNivel) {
    }
}
