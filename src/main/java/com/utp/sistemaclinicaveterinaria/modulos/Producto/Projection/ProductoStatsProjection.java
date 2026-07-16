package com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection;
import java.math.BigDecimal;
public interface ProductoStatsProjection {
    Integer getItems();
    Integer getCategorias();
    Integer getBajoStock();
    BigDecimal getValorTotal();
}
