package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ReporteResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<ReporteResponse> obtener() {
        return ApiResponse.ResponseAn(service.obtener());
    }

    @GetMapping("/detalle")
    public ApiResponse<List<DetalleResponse>> detalle(
            @RequestParam(required = false, defaultValue = "") String fechaInicio,
            @RequestParam(required = false, defaultValue = "") String fechaFin) {
        var data = service.detalle(fechaInicio, fechaFin);
        return ApiResponse.ResponseList(data, data.size());
    }
}
