package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/empleadoasociado")
public class EmpleadoAsociadoController {
    private final EmpleadoAsociadoService service;

    public EmpleadoAsociadoController(EmpleadoAsociadoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EmpleadoAsociadoCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<EmpleadoAsociadoListResponse>> listar(
            @RequestParam(required = false) Boolean estado,
            @RequestParam(required = false, defaultValue = "") String q) {
        var data = service.listar(new EmpleadoAsociadoDTO.EmpleadoAsociadoFilterRequest(q, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EmpleadoAsociadoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EmpleadoAsociadoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoAsociadoUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody EmpleadoAsociadoDeleteRequest d) {
        service.eliminar(d);
        return ApiResponse.Response("Eliminado con exito");
    }
}
