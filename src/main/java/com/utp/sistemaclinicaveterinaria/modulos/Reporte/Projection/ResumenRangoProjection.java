package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;

import java.math.BigDecimal;

// KPIs del reporte calculados dentro del rango de fechas elegido.
public interface ResumenRangoProjection {
    BigDecimal getIngresos();
    Integer getNumVentas();
    Integer getCitasAtendidas();
    Integer getCitasCanceladas();
    Integer getProductosVendidos();
    Integer getPacientesNuevos();
}
