package com.utp.sistemaclinicaveterinaria.modulos.Categoria;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<CategoriaCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<CategoriaListResponse>> listar(
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(required = false, defaultValue = "") String nombreCategoria) {
        var data = service.listar(new CategoriaDTO.CategoriaFilterRequest(nombreCategoria, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoriaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody CategoriaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody CategoriaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
