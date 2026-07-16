package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;
import java.math.BigDecimal;
public interface ResumenProjection {
    BigDecimal getIngresosMes();
    BigDecimal getIngresosMesAnterior();
    Integer getCitasAtendidas();
    Integer getCitasAtendidasAnterior();
    Integer getProductosVendidos();
    Integer getProductosVendidosAnterior();
    Integer getPacientesNuevos();
    Integer getPacientesNuevosAnterior();
}
