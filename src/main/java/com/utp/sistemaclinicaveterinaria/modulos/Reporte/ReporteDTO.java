package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReporteDTO {

    record ResumenResponse(
            BigDecimal ingresosMes, BigDecimal ingresosMesAnterior,
            Integer citasAtendidas, Integer citasAtendidasAnterior,
            Integer productosVendidos, Integer productosVendidosAnterior,
            Integer pacientesNuevos, Integer pacientesNuevosAnterior) {
    }

    record CitaSemanaResponse(LocalDate dia, Integer atendidas, Integer canceladas) {
    }

    record IngresoCategoriaResponse(String categoria, BigDecimal total) {
    }

    record ProductoTopResponse(String nombre, Integer cantidad) {
    }

    record PacienteMesResponse(LocalDate mes, Integer cantidad) {
    }

    record DetalleResponse(LocalDateTime fecha, String cliente, String item, String vendedor, BigDecimal total) {
    }

    record ReporteResponse(
            ResumenResponse resumen,
            List<CitaSemanaResponse> citasSemana,
            List<IngresoCategoriaResponse> ingresosCategoria,
            List<ProductoTopResponse> productosTop,
            List<PacienteMesResponse> pacientesMes) {
    }
}
