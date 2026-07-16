package com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection;

import java.math.BigDecimal;

public interface ProductoCatalogoProjection {
    Integer getIdProducto();
    String getNombre();
    BigDecimal getPrecioVenta();
    String getCategoria();
}
