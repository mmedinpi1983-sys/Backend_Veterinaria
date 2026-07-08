package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaUpdateRequest;
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
@RequestMapping("/api/raza")
public class RazaController {
    private final RazaService s;

    public RazaController(RazaService s) {
        this.s = s;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<RazaCatalogResponse>> catalogo() {
        var data = s.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @PostMapping("/listado")
    public ApiResponse<List<RazaListResponse>> listado(@Valid @RequestBody RazaFilterRequest f) {
        var data = s.listar(f);
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<RazaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        var data = s.obtenerId(id, UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseAn(data);
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody RazaCreateRequest r) {
        s.crear(r);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody RazaUpdateRequest r) {
        s.actualizar(id, r);
        return ApiResponse.Response("Modificado Existosamente");
    }

    @DeleteMapping
    public ApiResponse<Void> eliminar(@Valid @RequestBody RazaDeleteRequest r) {
        s.eliminar(r);
        return ApiResponse.Response("Eliminado con exito");
    }

}
