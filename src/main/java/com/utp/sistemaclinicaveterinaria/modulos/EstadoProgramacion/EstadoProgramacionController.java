package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/estadoprogramacion")
public class EstadoProgramacionController {
    private final EstadoProgramacionService service;

    public EstadoProgramacionController(EstadoProgramacionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EstadoProgramacionCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<EstadoProgramacionListResponse>> listar(
            @RequestParam(required = false) Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new EstadoProgramacionFilterRequest(nombre, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EstadoProgramacionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EstadoProgramacionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoProgramacionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody EstadoProgramacionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
