package com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VentaDetalleProjection {
    Integer getIdVenta();
    String getCodigoVenta();
    Integer getIdDueno();
    String getNombreComprador();
    String getDniComprador();
    String getTipoComprobante();
    LocalDateTime getFechaVenta();
    BigDecimal getSubTotal();
    BigDecimal getDescuento();
    BigDecimal getIgv();
    BigDecimal getTotal();
    BigDecimal getMontoPagado();
    Integer getEstadoVenta();
    Integer getIdMetodoPago();
    String getMetodoPago();
}
