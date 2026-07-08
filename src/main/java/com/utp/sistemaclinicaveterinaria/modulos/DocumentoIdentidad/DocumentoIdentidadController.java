package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/documentoidentidad")
public class DocumentoIdentidadController {
    private final DocumentoIdentidadService service;

    public DocumentoIdentidadController(DocumentoIdentidadService service) {
        this.service = service;
    }

    @GetMapping("/catalogo")
    public ApiResponse<List<DocumentoIdentidadCatalogResponse>> catalogo() {
        var data = service.catalogo(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<DocumentoIdentidadListResponse>> listar() {
        var data = service.listar(UsuarioActual.getAsociadoId());
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<DocumentoIdentidadDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Void> crear(@Valid @RequestBody DocumentoIdentidadCreateRequest c) {
        service.crear(c);
        return ApiResponse.Response("Creado Exitosamente");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> actualizar(@PathVariable Integer id, @Valid @RequestBody DocumentoIdentidadUpdateRequest t) {
        service.actualizar(id, t);
        return ApiResponse.Response("Modificado Exitosamente");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@Valid @RequestBody DocumentoIdentidadDeleteRequest t) {
        service.eliminar(t);
        return ApiResponse.Response("Eliminado con exito");
    }
}
