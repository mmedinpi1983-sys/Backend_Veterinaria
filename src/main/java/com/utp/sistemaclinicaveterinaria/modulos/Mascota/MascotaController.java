package com.utp.sistemaclinicaveterinaria.modulos.Mascota;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    private final MascotaService service;

    public MascotaController(MascotaService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<MascotaCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<MascotaListResponse>> listar(
            @RequestParam(required = false, defaultValue = "true") Boolean estado,
            @RequestParam(required = false, defaultValue = "") String q) {
        var data = service.listar(new MascotaDTO.MascotaFilterRequest(q, estado));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/buscar")
    public ApiResponse<List<SearchItem>> buscar(@RequestParam(required = false) String q) {
        List<SearchItem> data = service.buscar(q);
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<MascotaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id, UsuarioActual.getAsociadoId()));
    }

    @PostMapping("/crear")
    public ApiResponse<MascotaDTO.MascotaCreateResponse> crear(@Valid @RequestBody MascotaCreateRequest c) {
        Integer idMascota = service.crear(c);
        return ApiResponse.ResponseAn(new MascotaDTO.MascotaCreateResponse(idMascota));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody MascotaUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody MascotaDeleteRequest d) {
        service.eliminar(d);
        return ApiResponse.Response("Eliminado con exito");
    }
}
