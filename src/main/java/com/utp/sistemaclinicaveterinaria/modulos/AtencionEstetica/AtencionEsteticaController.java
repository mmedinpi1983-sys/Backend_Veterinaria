package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/atencionestetica")
public class AtencionEsteticaController {
    private final AtencionEsteticaService service;

    public AtencionEsteticaController(AtencionEsteticaService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AtencionEsteticaListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AtencionEsteticaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody AtencionEsteticaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AtencionEsteticaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AtencionEsteticaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
