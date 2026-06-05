package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.ListItem;
@RestController
@RequestMapping("/api/duenomascota")
public class DuenoMascotaController {
    private final DuenoMascotaService service;
    public DuenoMascotaController(DuenoMascotaService service) { this.service = service; }
    @GetMapping public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar(); return ApiResponse.ResponseList("datos", data, data.size());
    }
    @GetMapping("/{id}") public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) { return ApiResponse.ResponseAn("dato", service.obtenerPorId(id)); }
    @PostMapping public ApiResponse<Response> crear(@Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Creado", service.crear(request)); }
    @PutMapping("/{id}") public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request)); }
    @DeleteMapping("/{id}") public ApiResponse<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ApiResponse.Response("Eliminado"); }
}
