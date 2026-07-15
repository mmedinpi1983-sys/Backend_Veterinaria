package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.PermisoAccessService;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rolpermiso")
public class RolPermisoController {
    private final RolPermisoService service;
    private final PermisoAccessService permisoAccessService;

    public RolPermisoController(RolPermisoService service, PermisoAccessService permisoAccessService) {
        this.service = service;
        this.permisoAccessService = permisoAccessService;
    }

    // Módulos a los que tiene acceso el rol del usuario logueado (usado por el frontend
    // para mostrar/ocultar el sidebar y bloquear rutas).
    @GetMapping("/mismodulos")
    public ApiResponse<Map<String, Object>> misModulos() {
        var acceso = permisoAccessService.resolver(UsuarioActual.getIdRolesClinica(), UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseAn(Map.of("superAdmin", acceso.superAdmin(), "modulos", acceso.modulos()));
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<RolPermisoCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<RolPermisoListResponse>> listar() {
        var data = service.listar(new RolPermisoDTO.RolPermisoFilterRequest());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<RolPermisoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody RolPermisoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody RolPermisoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody RolPermisoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
