package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.ClaseResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MotivoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/movimientoproducto")
public class MovimientoProductoController {
    private final MovimientoProductoService service;

    public MovimientoProductoController(MovimientoProductoService service) {
        this.service = service;
    }

    @GetMapping("/clases")
    public ApiResponse<List<ClaseResponse>> clases() {
        var data = service.clases();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/motivos")
    public ApiResponse<List<MotivoResponse>> motivos() {
        var data = service.motivos();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/stats")
    public ApiResponse<MovimientoStatsResponse> stats() {
        return ApiResponse.ResponseAn(service.stats());
    }

    @GetMapping
    public ApiResponse<List<MovimientoListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false) Integer idClaseMovimiento) {
        var data = service.listar(new MovimientoProductoDTO.MovimientoFilterRequest(q, idClaseMovimiento));
        return ApiResponse.ResponseList(data, data.size());
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody MovimientoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Movimiento registrado");
    }
}
