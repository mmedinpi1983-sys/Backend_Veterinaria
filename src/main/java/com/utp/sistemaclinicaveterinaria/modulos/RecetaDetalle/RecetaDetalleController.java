package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/recetadetalle")
public class RecetaDetalleController {
    private final RecetaDetalleService service;

    public RecetaDetalleController(RecetaDetalleService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<RecetaDetalleCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<RecetaDetalleListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<RecetaDetalleDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody RecetaDetalleCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody RecetaDetalleUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody RecetaDetalleDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
