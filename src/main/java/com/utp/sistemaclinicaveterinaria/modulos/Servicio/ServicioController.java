package com.utp.sistemaclinicaveterinaria.modulos.Servicio;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {
    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<ServicioCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<ServicioListResponse>> listar(
            @RequestParam(required = false) Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombre) {
        var data = service.listar(new ServicioDTO.ServicioFilterRequest(nombre, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<ServicioDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody ServicioCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody ServicioUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody ServicioDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
