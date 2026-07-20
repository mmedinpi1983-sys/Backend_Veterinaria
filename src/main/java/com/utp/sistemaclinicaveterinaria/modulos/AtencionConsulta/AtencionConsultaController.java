package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaCreateResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/atencionconsulta")
public class AtencionConsultaController {
    private final AtencionConsultaService service;

    public AtencionConsultaController(AtencionConsultaService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<AtencionConsultaListResponse>> listar() {
        var data = service.listar();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<AtencionConsultaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<AtencionConsultaCreateResponse> crear(@Valid @RequestBody AtencionConsultaCreateRequest c) {
        Integer idConsulta = service.crear(c);
        return ApiResponse.ResponseAn(new AtencionConsultaCreateResponse(idConsulta));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody AtencionConsultaUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody AtencionConsultaDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
