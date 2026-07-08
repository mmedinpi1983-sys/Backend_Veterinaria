package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/estadosalidaatencion")
public class EstadoSalidaAtencionController {
    private final EstadoSalidaAtencionService service;

    public EstadoSalidaAtencionController(EstadoSalidaAtencionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EstadoSalidaAtencionCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<EstadoSalidaAtencionListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new EstadoSalidaAtencionDTO.EstadoSalidaAtencionFilterRequest(nombre));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EstadoSalidaAtencionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EstadoSalidaAtencionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoSalidaAtencionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody EstadoSalidaAtencionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
