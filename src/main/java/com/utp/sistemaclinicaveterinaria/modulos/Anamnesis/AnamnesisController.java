package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/anamnesis")
public class AnamnesisController {
    private final AnamnesisService service;

    public AnamnesisController(AnamnesisService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AnamnesisListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AnamnesisDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody AnamnesisCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AnamnesisUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AnamnesisDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
