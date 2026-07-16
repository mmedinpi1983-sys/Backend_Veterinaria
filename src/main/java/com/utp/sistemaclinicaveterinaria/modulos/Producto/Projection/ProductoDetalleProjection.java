package com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection;
import java.math.BigDecimal;
import java.time.LocalDate;
public interface ProductoDetalleProjection {
    Integer getIdProducto();
    String getNombre();
    Integer getIdCategoria();
    String getCategoria();
    Integer getStock();
    Integer getCantidadMinima();
    BigDecimal getPrecioVenta();
    BigDecimal getPrecioCompra();
    String getProveedor();
    LocalDate getFechaVencimiento();
    String getConcentracion();
    String getNotas();
    Boolean getEstado();
}
