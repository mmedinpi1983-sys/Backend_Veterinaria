package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;

// Controlador de mascotas - gestiona el registro de pacientes veterinarios
@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    private final MascotaService service;
    public MascotaController(MascotaService service) { this.service = service; }

    // GET /api/mascota - lista todas las mascotas activas
    @GetMapping public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar(); return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/mascota/buscar?q= - búsqueda en tiempo real incluyendo nombre del dueño, especie y raza
    @GetMapping("/buscar") public ApiResponse<List<SearchItem>> buscar(@RequestParam(required = false) String q) {
        List<SearchItem> data = service.buscar(q); return ApiResponse.ResponseList("datos", data, data.size());
    }

    // GET /api/mascota/{id} - obtiene una mascota por su ID
    @GetMapping("/{id}") public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) { return ApiResponse.ResponseAn("dato", service.obtenerPorId(id)); }

    // POST /api/mascota - registra una nueva mascota
    @PostMapping public ApiResponse<Response> crear(@Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Creado", service.crear(request)); }

    // PUT /api/mascota/{id} - actualiza datos de una mascota
    @PutMapping("/{id}") public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request)); }

    // DELETE /api/mascota/{id} - eliminación lógica (soft delete)
    @DeleteMapping("/{id}") public ApiResponse<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ApiResponse.Response("Eliminado"); }
}
