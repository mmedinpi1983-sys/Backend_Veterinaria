package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/presentacion")
public class PresentacionController {
    private final PresentacionService service;

    public PresentacionController(PresentacionService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<PresentacionCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<PresentacionListResponse>> listar(
            @RequestParam(required = false) Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new PresentacionDTO.PresentacionFilterRequest(nombre, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<PresentacionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody PresentacionCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody PresentacionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody PresentacionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
