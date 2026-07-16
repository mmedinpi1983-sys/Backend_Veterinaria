package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface VentaDTO {

    record ProductoCatalogoResponse(
            Integer idProducto,
            String nombre,
            BigDecimal precioVenta,
            String categoria) {
    }

    record MetodoPagoResponse(
            Integer idMetodoPago,
            String nombre) {
    }

    record VentaListResponse(
            Integer idVenta,
            String codigoVenta,
            LocalDateTime fechaVenta,
            String cliente,
            String tipoComprobante,
            String metodoPago,
            BigDecimal total,
            Integer estadoVenta) {
    }

    record VentaLineaResponse(
            Integer idVentaDetalle,
            String nombreItem,
            String tipo,
            Integer cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal) {
    }

    record VentaDetailResponse(
            Integer idVenta,
            String codigoVenta,
            Integer idDueno,
            String nombreComprador,
            String dniComprador,
            String tipoComprobante,
            LocalDateTime fechaVenta,
            BigDecimal subTotal,
            BigDecimal descuento,
            BigDecimal igv,
            BigDecimal total,
            BigDecimal montoPagado,
            Integer estadoVenta,
            Integer idMetodoPago,
            String metodoPago,
            List<VentaLineaResponse> items) {
    }

    record VentaItemRequest(
            Integer idProducto,
            Integer idServicio,
            @NotNull(message = "La cantidad es obligatoria")
            Integer cantidad,
            @NotNull(message = "El precio unitario es obligatorio")
            BigDecimal precioUnitario) {
    }

    record VentaCreateRequest(
            Integer idDueno,
            Integer idCita,
            Integer idAtencion,
            String tipoComprobante,
            String dniComprador,
            String nombreComprador,
            @NotNull(message = "El método de pago es obligatorio")
            Integer idMetodoPago,
            BigDecimal descuento,
            BigDecimal montoPagado,
            @NotEmpty(message = "La venta debe tener al menos un ítem")
            List<VentaItemRequest> items) {
    }

    record VentaFilterRequest(
            String q,
            String tipoComprobante) {
    }

    record VentaDeleteRequest(
            Integer idVenta) {
    }
}
