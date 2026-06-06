package com.utp.sistemaclinicaveterinaria.modulos.Atencion;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface AtencionDTO {
    record Request(
                 Integer idCitaProgramada,
                 Integer idTriaje,
                 Integer idAsociado,
                 LocalDate fechaAtencion,
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 String observacion,
                 Integer idEstadoSalida,
                 Integer idEstadoAtencion,
                 Integer idMascota
    ) {}
    record Response(
                 Integer idAtencion,
                 Integer idCitaProgramada,
                 Integer idTriaje,
                 Integer idAsociado,
                 LocalDate fechaAtencion,
                 LocalTime horaInicio,
                 LocalTime horaFin,
                 String observacion,
                 Integer idEstadoSalida,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEstadoAtencion,
                 Integer idMascota,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idAtencion,
                 String observacion,
                 LocalDateTime fechaCreacion
    ) {}
    record ListResponse(List<ListItem> items) {}

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
        Integer idReceta, java.time.LocalDateTime fechaReceta, List<MedicamentoReceta> detalle
    ) {}

    record DetalleCompleto(
        Response atencion,
        TriajeInfo triaje,
        ConsultaInfo consulta,
        AnamnesisInfo anamnesis,
        RecetaInfo receta
    ) {}
}
