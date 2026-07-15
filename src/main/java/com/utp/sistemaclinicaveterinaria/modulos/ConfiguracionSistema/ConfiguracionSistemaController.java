package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/configuracionsistema")
public class ConfiguracionSistemaController {
    private final ConfiguracionSistemaService service;

    public ConfiguracionSistemaController(ConfiguracionSistemaService service) {
        this.service = service;
    }

    @GetMapping("/mia")
    public ApiResponse<ConfiguracionSistemaResponse> mia() {
        return ApiResponse.ResponseAn(service.obtenerMia());
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody ConfiguracionSistemaCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody ConfiguracionSistemaUpdateRequest u) {
        service.actualizar(id, u);
        return ApiResponse.Response("Modificado Exitosamente");
    }
}
