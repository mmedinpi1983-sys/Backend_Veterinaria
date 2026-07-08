package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/metodoingreso")
public class MetodoIngresoController {
    private final MetodoIngresoService service;

    public MetodoIngresoController(MetodoIngresoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<MetodoIngresoCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<MetodoIngresoListResponse>> listar() {
        var data = service.listar(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<MetodoIngresoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody MetodoIngresoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody MetodoIngresoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody MetodoIngresoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
