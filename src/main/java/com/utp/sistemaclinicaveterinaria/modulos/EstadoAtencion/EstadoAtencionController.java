package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/estadoatencion")
public class EstadoAtencionController {
    private final EstadoAtencionService service;

    public EstadoAtencionController(EstadoAtencionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EstadoAtencionCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<EstadoAtencionListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new EstadoAtencionDTO.EstadoAtencionFilterRequest(nombre));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EstadoAtencionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EstadoAtencionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoAtencionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody EstadoAtencionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
