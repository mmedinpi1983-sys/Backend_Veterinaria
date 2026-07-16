package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.MetodoPagoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.ProductoCatalogoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {
    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping("/productos")
    public ApiResponse<List<ProductoCatalogoResponse>> productos() {
        var data = service.productosCatalogo();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/metodospago")
    public ApiResponse<List<MetodoPagoResponse>> metodosPago() {
        var data = service.metodosPago();
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping
    public ApiResponse<List<VentaListResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false, defaultValue = "") String tipoComprobante) {
        var data = service.listar(new VentaDTO.VentaFilterRequest(q, tipoComprobante));
        return ApiResponse.ResponseList(data, data.size());
    }

    @GetMapping("/{id}")
    public ApiResponse<VentaDetailResponse> obtenerPorId(@PathVariable Integer id) {
        return ApiResponse.ResponseAn(service.obtenerId(id));
    }

    @PostMapping("/crear")
    public ApiResponse<Integer> crear(@Valid @RequestBody VentaCreateRequest c) {
        Integer idVenta = service.crear(c);
        return ApiResponse.ResponseAn(idVenta);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> anular(@PathVariable Integer id) {
        service.anular(new VentaDeleteRequest(id));
        return ApiResponse.Response("Venta anulada con éxito");
    }
}
