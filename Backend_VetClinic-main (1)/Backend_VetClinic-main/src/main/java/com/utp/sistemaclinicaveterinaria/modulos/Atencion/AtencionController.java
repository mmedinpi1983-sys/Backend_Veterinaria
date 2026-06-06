package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.ListItem;

// Controlador de atención clínica - gestiona el flujo completo de atención: triaje, anamnesis, consulta y receta
@RestController
@RequestMapping("/api/atencion")
public class AtencionController {

    private final AtencionService service;

    public AtencionController(AtencionService service) { this.service = service; }

    // GET /api/atencion - lista todas las atenciones registradas
    @GetMapping
    public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar();
        return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/atencion/{id} - obtiene una atención por ID
    @GetMapping("/{id}")
    public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn("dato", service.obtenerPorId(id));
    }

    // GET /api/atencion/{id}/detalle - retorna la atención completa con triaje, anamnesis, consulta y receta
    @GetMapping("/{id}/detalle")
    public ApiResponse<DetalleCompleto> obtenerDetalle(@PathVariable Integer id) {
        return ApiResponse.ResponseAn("detalle", service.obtenerDetalle(id));
    }

    // GET /api/atencion/por-cita/{idCita} - busca la atención vinculada a una cita específica
    @GetMapping("/por-cita/{idCita}")
    public ApiResponse<Response> obtenerPorCita(@PathVariable Integer idCita) {
        return ApiResponse.ResponseAn("dato", service.obtenerPorCita(idCita));
    }

    // GET /api/atencion/historial/{idMascota} - historial clínico completo de una mascota
    @GetMapping("/historial/{idMascota}")
    public ApiResponse<List<HistorialView>> getHistorial(@PathVariable Integer idMascota) {
        List<HistorialView> data = service.getHistorialByMascota(idMascota);
        return ApiResponse.ResponseList("historial", data, data.size());
    }

    // POST /api/atencion - registra una nueva atención
    @PostMapping
    public ApiResponse<Response> crear(@Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Creado", service.crear(request));
    }

    // PUT /api/atencion/{id} - actualiza una atención existente
    @PutMapping("/{id}")
    public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) {
        return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request));
    }

    // DELETE /api/atencion/{id} - eliminación lógica
    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ApiResponse.Response("Eliminado");
    }
}
