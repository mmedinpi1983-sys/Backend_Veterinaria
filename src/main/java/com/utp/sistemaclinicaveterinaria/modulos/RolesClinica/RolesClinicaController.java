package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/rolesclinica")
public class RolesClinicaController {
    private final RolesClinicaService service;

    public RolesClinicaController(RolesClinicaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<RolesClinicaCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<RolesClinicaListResponse>> listar(
            @RequestParam(required = false) Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombreRol) {
        var data = service.listar(new RolesClinicaDTO.RolesClinicaFilterRequest(nombreRol, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<RolesClinicaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody RolesClinicaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody RolesClinicaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody RolesClinicaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
