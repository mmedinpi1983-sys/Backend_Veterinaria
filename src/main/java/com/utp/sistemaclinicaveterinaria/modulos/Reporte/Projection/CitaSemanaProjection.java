package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;
import java.time.LocalDate;
public interface CitaSemanaProjection {
    LocalDate getDia();
    Integer getAtendidas();
    Integer getCanceladas();
}
