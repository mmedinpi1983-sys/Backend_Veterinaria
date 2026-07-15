package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

public interface ConfiguracionSistemaDTO {

    record ConfiguracionSistemaResponse(
            Integer idConfiguracionSistema,
            Integer idAsociado,
            String zonaHoraria,
            String formatoFecha,
            String moneda) {
    }

    record ConfiguracionSistemaCreateRequest(
            String zonaHoraria,
            String formatoFecha,
            String moneda) {
    }

    record ConfiguracionSistemaUpdateRequest(
            String zonaHoraria,
            String formatoFecha,
            String moneda) {
    }
}
