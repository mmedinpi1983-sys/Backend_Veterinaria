package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/especie")
public class EspecieController {
    private final EspecieService s;

    public EspecieController(EspecieService s) {
        this.s = s;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<EspecieCatalogResponse>> catalogo() {
        var data = s.catalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @PostMapping("/listado")
    public ApiResponse<List<EspecieListResponse>> listado(@Valid @RequestBody EspecieFilterRequest f) {
        var data = s.listar(f);
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<EspecieDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(s.obtenerPorId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody EspecieCreateRequest c) {
        s.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody EspecieUpdateRequest e) {
        s.actualizar(id, e);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping
    public ApiResponse<Void> eliminar(@Valid @RequestBody EspecieDeleteRequest e) {
        s.eliminar(e);
        return ApiResponse.Response("Eliminado con exito");
    }

}
