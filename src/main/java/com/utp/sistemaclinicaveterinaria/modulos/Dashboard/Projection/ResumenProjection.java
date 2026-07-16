package com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection;

import java.math.BigDecimal;

public interface ResumenProjection {
    Integer getCitasHoyTotal();
    Integer getCitasHoyCompletadas();
    Integer getCitasHoyPendientes();
    Integer getPacientesActivos();
    Integer getPacientesNuevosMes();
    BigDecimal getIngresosHoy();
    BigDecimal getIngresosAyer();
    Integer getAlertasStock();
}
