package com.utp.sistemaclinicaveterinaria.modulos.Permiso;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/permiso")
public class PermisoController {
    private final PermisoService service;

    public PermisoController(PermisoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<PermisoCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<PermisoListResponse>> listar(
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombrePermiso,
            @RequestParam(required = false, defaultValue = "") String descripcionPermiso) {
        var data = service.listar(new PermisoDTO.PermisoFilterRequest(nombrePermiso, descripcionPermiso, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<PermisoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody PermisoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody PermisoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody PermisoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
