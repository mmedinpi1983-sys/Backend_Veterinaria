package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/estadocita")
public class EstadoCitaController {
    private final EstadoCitaService service;

    public EstadoCitaController(EstadoCitaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EstadoCitaCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<EstadoCitaListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new EstadoCitaDTO.EstadoCitaFilterRequest(nombre));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EstadoCitaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EstadoCitaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EstadoCitaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody EstadoCitaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
