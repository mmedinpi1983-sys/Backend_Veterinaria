package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/triajedetalle")
public class TriajeDetalleController {
    private final TriajeDetalleService service;

    public TriajeDetalleController(TriajeDetalleService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<TriajeDetalleCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<TriajeDetalleListResponse>> listar() {
        var data = service.listar(new TriajeDetalleDTO.TriajeDetalleFilterRequest());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<TriajeDetalleDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody TriajeDetalleCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody TriajeDetalleUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody TriajeDetalleDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
