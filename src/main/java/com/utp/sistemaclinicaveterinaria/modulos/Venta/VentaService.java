package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.MetodoPagoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.ProductoCatalogoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaListResponse;

public interface VentaService {
    List<ProductoCatalogoResponse> productosCatalogo();
    List<MetodoPagoResponse> metodosPago();
    List<VentaListResponse> listar(VentaFilterRequest f);
    VentaDetailResponse obtenerId(Integer idVenta);
    Integer crear(VentaCreateRequest c);
    void anular(VentaDeleteRequest d);
}
