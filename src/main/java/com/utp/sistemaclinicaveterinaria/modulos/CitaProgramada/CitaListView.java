package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CitaListView {
    Integer getIdCitaProgramada();
    LocalDate getFecha();
    LocalTime getHoraInicio();
    LocalTime getHoraFin();
    String getNombreMascota();
    String getEspecie();
    String getRaza();
    String getNombreDueno();
    String getNombreServicio();
    String getNombreVeterinario();
    String getEstadoCita();
    Integer getIdEstadoCita();
    String getMotivo();
}
