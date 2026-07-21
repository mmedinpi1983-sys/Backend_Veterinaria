package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ReporteResponse;

public interface ReporteService {
    ReporteResponse obtener();
    List<DetalleResponse> detalle(String fechaInicio, String fechaFin);
    byte[] generarExcel(List<String> tipos, String fechaInicio, String fechaFin);
}
