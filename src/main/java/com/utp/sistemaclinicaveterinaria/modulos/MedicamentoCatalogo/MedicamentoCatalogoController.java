package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentocatalogo")
public class MedicamentoCatalogoController {
    private final MedicamentoCatalogoService service;

    public MedicamentoCatalogoController(MedicamentoCatalogoService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<MedicamentoCatalogoCatalogResponse>> catalogo() {
        var data = service.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<MedicamentoCatalogoListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<MedicamentoCatalogoDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody MedicamentoCatalogoCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody MedicamentoCatalogoUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody MedicamentoCatalogoDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
