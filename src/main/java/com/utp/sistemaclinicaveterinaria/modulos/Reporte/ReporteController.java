package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ReporteResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import java.util.Arrays;
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

    // Genera y descarga el reporte en Excel (.xlsx). tipos = lista separada por comas:
    // resumen, ventas, citas, veterinarios.
    @GetMapping("/excel")
    public ResponseEntity<byte[]> excel(
            @RequestParam(required = false, defaultValue = "") String tipos,
            @RequestParam(required = false, defaultValue = "") String fechaInicio,
            @RequestParam(required = false, defaultValue = "") String fechaFin) {
        List<String> lista = tipos.isBlank() ? List.of() : Arrays.asList(tipos.split(","));
        byte[] contenido = service.generarExcel(lista, fechaInicio, fechaFin);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"reporte.xlsx\"")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(contenido);
    }
}
