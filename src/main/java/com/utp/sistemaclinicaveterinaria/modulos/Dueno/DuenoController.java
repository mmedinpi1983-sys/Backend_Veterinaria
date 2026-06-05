package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.ListItem;
@RestController
@RequestMapping("/api/dueno")
public class DuenoController {
    private final DuenoService service;
    public DuenoController(DuenoService service) { this.service = service; }
    @GetMapping public ApiResponse<List<ListItem>> listar() {
        List<ListItem> data = service.listar(); return ApiResponse.ResponseList("datos", data, data.size());
    }
    @GetMapping("/{id}") public ApiResponse<Response> obtenerPorId(@PathVariable Integer id) { return ApiResponse.ResponseAn("dato", service.obtenerPorId(id)); }
    @PostMapping public ApiResponse<Response> crear(@Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Creado", service.crear(request)); }
    @PutMapping("/{id}") public ApiResponse<Response> actualizar(@PathVariable Integer id, @Valid @RequestBody Request request) { return ApiResponse.ResponseAn("Actualizado", service.actualizar(id, request)); }
    @DeleteMapping("/{id}") public ApiResponse<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ApiResponse.Response("Eliminado"); }
}
