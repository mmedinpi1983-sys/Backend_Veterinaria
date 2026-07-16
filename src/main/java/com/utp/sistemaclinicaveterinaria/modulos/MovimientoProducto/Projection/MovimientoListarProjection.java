package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection;
import java.time.LocalDateTime;
public interface MovimientoListarProjection {
    Integer getIdMovimiento();
    LocalDateTime getFecha();
    String getProducto();
    String getTipo();
    Integer getCantidad();
    Integer getStockAnterior();
    Integer getStockNuevo();
    String getMotivo();
    String getEmpleado();
}
