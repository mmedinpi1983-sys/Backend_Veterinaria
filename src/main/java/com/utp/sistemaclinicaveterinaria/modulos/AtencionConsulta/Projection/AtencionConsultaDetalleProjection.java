package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AtencionConsultaDetalleProjection {
    Integer getIdConsulta();
    Integer getIdAtencion();
    String getEvaluacionClinica();
    String getTratamiento();
    String getIndicaciones();
    String getObservaciones();
    Boolean getRequiereControl();
    LocalDate getFechaProximoControl();
    String getMotivoConsulta();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
