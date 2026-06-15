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
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.VeterinarioDisponible;

// Controlador de citas programadas - gestiona el agendamiento de consultas veterinarias
@RestController
@RequestMapping("/api/citaprogramada")
public class CitaProgramadaController {

    private final CitaProgramadaService service;

    public CitaProgramadaController(CitaProgramadaService service) { this.service = service; }

    // GET /api/citaprogramada - lista básica de citas
    @GetMapping
    public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar();
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/citaprogramada/enriquecida - lista con datos completos: mascota, dueño, veterinario, estado
    // Acepta filtros opcionales: idEstado, idEmpleado, fechaInicio, fechaFin, busqueda
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

    // GET /api/citaprogramada/stats - contadores del mes actual: total, pendientes, completadas, canceladas
    @GetMapping("/stats")
    public ApiResponse<StatsResponse> getStats() {
        return ApiResponse.ResponseAn("stats", service.getStats());
    }

    // GET /api/citaprogramada/veterinarios - veterinarios disponibles con su turno (para el select de Nueva Cita)
    @GetMapping("/veterinarios")
    public ApiResponse<List<VeterinarioDisponible>> listarVeterinarios() {
        List<VeterinarioDisponible> data = service.listarVeterinarios();
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/citaprogramada/horas-ocupadas?idProgramacion=&fecha= - horas (HH:mm) ya tomadas por ese veterinario ese día
    @GetMapping("/horas-ocupadas")
    public ApiResponse<List<String>> horasOcupadas(
            @RequestParam Integer idProgramacion,
            @RequestParam String fecha,
            @RequestParam(required = false) Integer excluir) {
        List<String> data = service.horasOcupadas(idProgramacion, fecha, excluir);
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/citaprogramada/{id} - obtiene una cita por ID
    @GetMapping("/{id}")
    public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn("dato", service.obtenerPorId(id));
    }

    // POST /api/citaprogramada - registra una nueva cita
    @PostMapping
    public ApiResponse<Response> crear(@Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Creado", service.crear(request));
    }

    // PUT /api/citaprogramada/{id} - actualiza una cita (también usado para cancelar y reprogramar)
    @PutMapping("/{id}")
    public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request));
    }

    // DELETE /api/citaprogramada/{id} - eliminación lógica
    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ApiResponse.Response("Eliminado");
    }

    // GET /api/citaprogramada/por-mascota/{idMascota} - historial de citas de una mascota específica
    @GetMapping("/por-mascota/{idMascota}")
    public ApiResponse<List<CitaEnriquecida>> listarPorMascota(@PathVariable Integer idMascota) {
        List<CitaEnriquecida> data = service.listarPorMascota(idMascota);
        return ApiResponse.ResponseList("datos", data, data.size());
    }
}
