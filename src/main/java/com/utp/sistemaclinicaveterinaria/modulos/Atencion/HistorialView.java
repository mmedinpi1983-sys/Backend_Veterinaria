package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

public interface HistorialView {
    Integer getIdAtencion();
    String getFechaAtencion();
    String getEvaluacionClinica();
    String getTratamiento();
    String getFechaProximoControl();
    String getMotivoConsulta();
}
