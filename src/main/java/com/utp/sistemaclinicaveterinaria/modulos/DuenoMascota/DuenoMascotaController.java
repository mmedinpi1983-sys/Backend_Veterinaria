package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/duenomascota")
public class DuenoMascotaController {
    private final DuenoMascotaService service;

    public DuenoMascotaController(DuenoMascotaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<DuenoMascotaCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<DuenoMascotaListResponse>> listar() {
        var data = service.listar(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<DuenoMascotaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody DuenoMascotaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody DuenoMascotaUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody DuenoMascotaDeleteRequest d) {
        service.eliminar(d);
        return ApiResponse.Response("Eliminado con exito");
    }
}
