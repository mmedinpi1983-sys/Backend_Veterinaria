package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.CategoriaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<ProductoCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/categorias")
    public ApiResponse<List<CategoriaResponse>> categorias() {
        var data = service.categorias();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/stats")
    public ApiResponse<ProductoStatsResponse> stats() {
        return ApiResponse.ResponseAn(service.stats());
    }

    @GetMapping
    public ApiResponse<List<ProductoListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false) Integer idCategoria) {
        var data = service.listar(new ProductoDTO.ProductoFilterRequest(q, idCategoria));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody ProductoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(new ProductoDeleteRequest(id));
        return ApiResponse.Response("Eliminado con exito");
    }
}
