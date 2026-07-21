package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;

import java.time.LocalDate;

public interface CitaDetalleProjection {
    LocalDate getFecha();
    String getMascota();
    String getDueno();
    String getEspecie();
    String getRaza();
    String getServicio();
    String getVeterinario();
    String getEstado();
    String getMotivo();
}
