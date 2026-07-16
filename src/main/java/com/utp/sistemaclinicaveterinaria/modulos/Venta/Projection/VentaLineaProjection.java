package com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection;

import java.math.BigDecimal;

public interface VentaLineaProjection {
    Integer getIdVentaDetalle();
    String getNombreItem();
    String getTipo();
    Integer getCantidad();
    BigDecimal getPrecioUnitario();
    BigDecimal getSubtotal();
}
