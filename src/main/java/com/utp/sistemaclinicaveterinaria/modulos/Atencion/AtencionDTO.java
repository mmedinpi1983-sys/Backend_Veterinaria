package com.utp.sistemaclinicaveterinaria.modulos.Atencion;
import java.time.*;
import java.util.List;

public interface AtencionDTO {

    record AtencionListResponse(
            Integer idAtencion,
            String observacion,
            LocalDateTime fechaCreacion) {
    }

    record AtencionDetailResponse(
            Integer idAtencion,
            Integer idCitaProgramada,
            Integer idTriaje,
            LocalDate fechaAtencion,
            LocalTime horaInicio,
            LocalTime horaFin,
            String observacion,
            Integer idEstadoSalida,
            Integer idEstadoAtencion,
            Integer idMascota,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record AtencionCreateRequest(
            Integer idCitaProgramada,
            Integer idTriaje,
            LocalDate fechaAtencion,
            LocalTime horaInicio,
            LocalTime horaFin,
            String observacion,
            Integer idEstadoSalida,
            Integer idEstadoAtencion,
            Integer idMascota) {
    }

    record AtencionCreateResponse(
            Integer idAtencion) {
    }

    record AtencionUpdateRequest(
            Integer idCitaProgramada,
            Integer idTriaje,
            LocalDate fechaAtencion,
            LocalTime horaInicio,
            LocalTime horaFin,
            String observacion,
            Integer idEstadoSalida,
            Integer idEstadoAtencion,
            Integer idMascota) {
    }

    record AtencionFilterRequest(
            String observacion) {
    }

    record AtencionDeleteRequest(
            Integer idAtencion) {
    }

    record TriajeInfo(
        Integer idTriaje, String codigoTemporal, Integer prioridad, Integer idMetodoIngreso,
        java.math.BigDecimal temperatura, java.math.BigDecimal peso, String alergias, String observaciones
    ) {}

    record ConsultaInfo(
        Integer idConsulta, String motivoConsulta, String evaluacionClinica,
        String tratamiento, String indicaciones, String observaciones,
        Boolean requiereControl, java.time.LocalDate fechaProximoControl
    ) {}

    record AnamnesisInfo(
        Integer idAnamnesis, String antecedentes, Integer alergias, String detalleAlergias,
        Integer cirugiasAnteriores, String detalleCirugias, String medicamentosActuales,
        String historialVacunacion, String alimentacion, String comportamiento,
        Integer historialReproductivo, String inicioSintomas, String evolucionSintomas, String observaciones
    ) {}

    record MedicamentoReceta(
        Integer idRecetaDetalle, Integer idMedicamento,
        String dosis, String frecuencia, String duracion, String indicacionesEspecificas
    ) {}

    record RecetaInfo(
        Integer idReceta, LocalDateTime fechaReceta, List<MedicamentoReceta> detalle
    ) {}

    record DetalleCompleto(
        AtencionDetailResponse atencion,
        TriajeInfo triaje,
        ConsultaInfo consulta,
        AnamnesisInfo anamnesis,
        RecetaInfo receta
    ) {}
}
