package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;
import java.time.*;

public interface AnamnesisDTO {

    record AnamnesisListResponse(
            Integer idAnamnesis,
            Integer idConsulta,
            String antecedentes,
            String medicamentosActuales,
            String alimentacion,
            LocalDateTime fechaCreacion) {
    }

    record AnamnesisDetailResponse(
            Integer idAnamnesis,
            Integer idConsulta,
            String antecedentes,
            Integer alergias,
            Integer cirugiasAnteriores,
            String medicamentosActuales,
            String alimentacion,
            String comportamiento,
            String inicioSintomas,
            String evolucionSintomas,
            String observaciones,
            String detalleAlergias,
            String detalleCirugias,
            String historialVacunacion,
            String estiloVida,
            Integer historialReproductivo,
            String reproduccionDetalle,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AnamnesisCreateRequest(
            Integer idConsulta,
            String antecedentes,
            Integer alergias,
            Integer cirugiasAnteriores,
            String medicamentosActuales,
            String alimentacion,
            String comportamiento,
            String inicioSintomas,
            String evolucionSintomas,
            String observaciones,
            String detalleAlergias,
            String detalleCirugias,
            String historialVacunacion,
            String estiloVida,
            Integer historialReproductivo,
            String reproduccionDetalle) {
    }

    record AnamnesisUpdateRequest(
            Integer idConsulta,
            String antecedentes,
            Integer alergias,
            Integer cirugiasAnteriores,
            String medicamentosActuales,
            String alimentacion,
            String comportamiento,
            String inicioSintomas,
            String evolucionSintomas,
            String observaciones,
            String detalleAlergias,
            String detalleCirugias,
            String historialVacunacion,
            String estiloVida,
            Integer historialReproductivo,
            String reproduccionDetalle) {
    }

    record AnamnesisFilterRequest(
            Integer idConsulta,
            String antecedentes) {
    }

    record AnamnesisDeleteRequest(
            Integer idAnamnesis) {
    }
}
