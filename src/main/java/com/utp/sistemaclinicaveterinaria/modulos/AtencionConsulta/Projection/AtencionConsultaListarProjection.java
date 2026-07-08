package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection;

import java.time.LocalDateTime;

public interface AtencionConsultaListarProjection {
    Integer getIdConsulta();
    String getEvaluacionClinica();
    String getTratamiento();
    String getIndicaciones();
    LocalDateTime getFechaCreacion();
}
