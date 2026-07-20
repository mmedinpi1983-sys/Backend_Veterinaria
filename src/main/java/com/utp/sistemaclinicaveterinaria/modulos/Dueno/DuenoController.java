package com.utp.sistemaclinicaveterinaria.modulos.Dueno;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/dueno")
public class DuenoController {
    private final DuenoService service;

    public DuenoController(DuenoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<DuenoCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<DuenoListResponse>> listar(
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(required = false, defaultValue = "") String q) {
        var data = service.listar(new DuenoDTO.DuenoFilterRequest(q, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<DuenoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<DuenoDTO.DuenoCreateResponse> crear(@Valid @RequestBody DuenoCreateRequest c) {
        Integer idDueno = service.crear(c);
        return ApiResponse.ResponseAn(new DuenoDTO.DuenoCreateResponse(idDueno));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody DuenoUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody DuenoDeleteRequest d) {
        service.eliminar(d);
        return ApiResponse.Response("Eliminado con exito");
    }
}
