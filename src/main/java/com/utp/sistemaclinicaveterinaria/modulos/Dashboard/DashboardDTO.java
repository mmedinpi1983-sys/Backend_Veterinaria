package com.utp.sistemaclinicaveterinaria.modulos.Dashboard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DashboardDTO {

    record ResumenResponse(
            Integer citasHoyTotal,
            Integer citasHoyCompletadas,
            Integer citasHoyPendientes,
            Integer pacientesActivos,
            Integer pacientesNuevosMes,
            BigDecimal ingresosHoy,
            BigDecimal ingresosAyer,
            Integer alertasStock) {
    }

    record CitaSemanaResponse(
            LocalDate dia,
            Integer cantidad) {
    }

    record AlertaStockResponse(
            String nombre,
            String categoria,
            Integer stock,
            Integer minimo) {
    }

    record DashboardResponse(
            ResumenResponse resumen,
            List<CitaSemanaResponse> citasSemana,
            List<AlertaStockResponse> alertasStock) {
    }
}
