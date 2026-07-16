package com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection;
import java.math.BigDecimal;
import java.time.LocalDate;
public interface ProductoListarProjection {
    Integer getIdProducto();
    String getNombre();
    String getCategoria();
    Integer getIdCategoria();
    Integer getStock();
    Integer getCantidadMinima();
    BigDecimal getPrecioVenta();
    String getProveedor();
    LocalDate getFechaVencimiento();
    Boolean getEstado();
}
