package com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection;
import java.math.BigDecimal;
public interface ProductoCatalogoProjection {
    Integer getIdProducto();
    String getNombre();
    BigDecimal getPrecioVenta();
    Integer getStock();
}
