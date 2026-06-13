package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;

@RestController
@RequestMapping("/api/citaprogramada")
public class CitaProgramadaController {

    private final CitaProgramadaService service;

    public CitaProgramadaController(CitaProgramadaService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar();
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    @GetMapping("/enriquecida")
    public ApiResponse<List<CitaEnriquecida>> listarEnriquecido(
            @RequestParam(required = false) Integer idEstado,
            @RequestParam(required = false) Integer idEmpleado,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) String busqueda) {
        List<CitaEnriquecida> data = service.listarEnriquecido(idEstado, idEmpleado, fechaInicio, fechaFin, busqueda);
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    @GetMapping("/stats")
    public ApiResponse<StatsResponse> getStats() {
        return ApiResponse.ResponseAn("stats", service.getStats());
    }

    @GetMapping("/{id}")
    public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn("dato", service.obtenerPorId(id));
    }

    @PostMapping
    public ApiResponse<Response> crear(@Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Creado", service.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ApiResponse.Response("Eliminado");
    }
}
