package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/atencion")
public class AtencionController {
    private final AtencionService service;

    public AtencionController(AtencionService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AtencionListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AtencionDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @GetMapping("/{id}/detalle")
    public ApiResponse<DetalleCompleto> obtenerDetalle(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerDetalle(id));
    }

    @GetMapping("/por-cita/{idCita}")
    public ApiResponse<AtencionDetailResponse> obtenerPorCita(@PathVariable Integer idCita) {
        return ApiResponse.ResponseAn(service.obtenerPorCita(idCita));
    }

    @GetMapping("/historial/{idMascota}")
    public ApiResponse<List<HistorialView>> getHistorial(@PathVariable Integer idMascota) {
        var data = service.getHistorialByMascota(idMascota);
        return ApiResponse.ResponseList(data, data.size());
    }

    @PostMapping("/crear")
    public ApiResponse<AtencionDTO.AtencionCreateResponse> crear(@Valid @RequestBody AtencionCreateRequest c) {
        Integer idAtencion = service.crear(c);
        return ApiResponse.ResponseAn(new AtencionDTO.AtencionCreateResponse(idAtencion));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AtencionUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AtencionDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
