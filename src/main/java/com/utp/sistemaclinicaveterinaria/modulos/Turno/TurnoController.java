package com.utp.sistemaclinicaveterinaria.modulos.Turno;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    private final TurnoService service;

    public TurnoController(TurnoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<TurnoCatalogResponse>> listar() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<TurnoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody TurnoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody TurnoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody TurnoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
