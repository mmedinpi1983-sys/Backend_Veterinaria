package com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VentaListarProjection {
    Integer getIdVenta();
    String getCodigoVenta();
    LocalDateTime getFechaVenta();
    String getCliente();
    String getTipoComprobante();
    String getMetodoPago();
    BigDecimal getTotal();
    Integer getEstadoVenta();
}
