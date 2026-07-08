package com.utp.sistemaclinicaveterinaria.modulos.Asociado;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/asociado")
public class AsociadoController {
    private final AsociadoService service;

    public AsociadoController(AsociadoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AsociadoListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AsociadoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody AsociadoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AsociadoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AsociadoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
