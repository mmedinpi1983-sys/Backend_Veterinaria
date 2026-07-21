package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/consultorio")
public class ConsultorioController {
    private final ConsultorioService service;

    public ConsultorioController(ConsultorioService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<ConsultorioCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<ConsultorioListResponse>> listar(
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new ConsultorioDTO.ConsultorioFilterRequest(nombre, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<ConsultorioDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody ConsultorioCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody ConsultorioUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody ConsultorioDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
