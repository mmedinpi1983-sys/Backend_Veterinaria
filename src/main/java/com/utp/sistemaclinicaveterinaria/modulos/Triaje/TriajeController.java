package com.utp.sistemaclinicaveterinaria.modulos.Triaje;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCreateResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/triaje")
public class TriajeController {
    private final TriajeService service;

    public TriajeController(TriajeService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<TriajeCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<TriajeListResponse>> listar() {
        var data = service.listar(new TriajeDTO.TriajeFilterRequest());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<TriajeDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<TriajeCreateResponse> crear(@Valid @RequestBody TriajeCreateRequest c) {
        Integer idTriaje = service.crear(c);
        return ApiResponse.ResponseAn(new TriajeCreateResponse(idTriaje));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody TriajeUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody TriajeDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
