package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.MetodoPagoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.ProductoCatalogoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaLineaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.MetodoPagoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.ProductoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaLineaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VentaMapper {

    VentaListResponse toListResponse(VentaListarProjection p);

    List<VentaListResponse> toListResponse(List<VentaListarProjection> p);

    VentaLineaResponse toLineaResponse(VentaLineaProjection p);

    List<VentaLineaResponse> toLineaResponse(List<VentaLineaProjection> p);

    ProductoCatalogoResponse toProductoResponse(ProductoCatalogoProjection p);

    List<ProductoCatalogoResponse> toProductoResponse(List<ProductoCatalogoProjection> p);

    MetodoPagoResponse toMetodoPagoResponse(MetodoPagoProjection p);

    List<MetodoPagoResponse> toMetodoPagoResponse(List<MetodoPagoProjection> p);
}
