package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/programacion")
public class ProgramacionController {
    private final ProgramacionService service;

    public ProgramacionController(ProgramacionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<ProgramacionCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<ProgramacionListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProgramacionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody ProgramacionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody ProgramacionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody ProgramacionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
