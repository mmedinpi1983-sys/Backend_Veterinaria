package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/atencionvacunacion")
public class AtencionVacunacionController {
    private final AtencionVacunacionService service;

    public AtencionVacunacionController(AtencionVacunacionService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AtencionVacunacionListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AtencionVacunacionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody AtencionVacunacionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AtencionVacunacionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AtencionVacunacionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
