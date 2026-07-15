package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticocatalogo")
public class DiagnosticoCatalogoController {
    private final DiagnosticoCatalogoService service;

    public DiagnosticoCatalogoController(DiagnosticoCatalogoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<DiagnosticoCatalogoCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<DiagnosticoCatalogoListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<DiagnosticoCatalogoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody DiagnosticoCatalogoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody DiagnosticoCatalogoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody DiagnosticoCatalogoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
