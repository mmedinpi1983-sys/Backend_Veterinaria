package com.utp.sistemaclinicaveterinaria.modulos.Receta;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/receta")
public class RecetaController {
    private final RecetaService service;

    public RecetaController(RecetaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<RecetaCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<RecetaListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<RecetaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody RecetaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody RecetaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody RecetaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
