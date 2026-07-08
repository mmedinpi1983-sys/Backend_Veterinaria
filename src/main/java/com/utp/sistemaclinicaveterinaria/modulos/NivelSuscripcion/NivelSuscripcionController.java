package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/nivelsuscripcion")
public class NivelSuscripcionController {
    private final NivelSuscripcionService service;

    public NivelSuscripcionController(NivelSuscripcionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<NivelSuscripcionCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<NivelSuscripcionListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<NivelSuscripcionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody NivelSuscripcionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody NivelSuscripcionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody NivelSuscripcionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
