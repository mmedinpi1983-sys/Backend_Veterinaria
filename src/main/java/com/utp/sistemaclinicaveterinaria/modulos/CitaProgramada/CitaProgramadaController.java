package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.VeterinarioDisponible;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ProgramacionCita;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/citaprogramada")
public class CitaProgramadaController {

    private final CitaProgramadaService service;

    public CitaProgramadaController(CitaProgramadaService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<CitaProgramadaListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/enriquecida")
    public ApiResponse<List<CitaEnriquecida>> listarEnriquecido(
            @RequestParam(required = false) Integer idEstado,
            @RequestParam(required = false) Integer idEmpleado,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) String busqueda) {
        var data = service.listarEnriquecido(idEstado, idEmpleado, fechaInicio, fechaFin, busqueda);
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/stats")
    public ApiResponse<StatsResponse> getStats() {
        return ApiResponse.ResponseAn(service.getStats());
    }

    @GetMapping("/programaciones")
    public ApiResponse<List<ProgramacionCita>> listarProgramacionesCita() {
        var data = service.listarProgramacionesCita();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/veterinarios")
    public ApiResponse<List<VeterinarioDisponible>> listarVeterinarios() {
        var data = service.listarVeterinarios();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/horas-ocupadas")
    public ApiResponse<List<String>> horasOcupadas(
            @RequestParam Integer idProgramacion,
            @RequestParam String fecha,
            @RequestParam(required = false) Integer excluir) {
        var data = service.horasOcupadas(idProgramacion, fecha, excluir);
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<CitaProgramadaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody CitaProgramadaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody CitaProgramadaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody CitaProgramadaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }

    @GetMapping("/por-mascota/{idMascota}")
    public ApiResponse<List<CitaEnriquecida>> listarPorMascota(@PathVariable Integer idMascota) {
        var data = service.listarPorMascota(idMascota);
        return ApiResponse.ResponseList(data, data.size());
    }
}
