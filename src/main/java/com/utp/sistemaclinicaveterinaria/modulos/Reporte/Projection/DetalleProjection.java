package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public interface DetalleProjection {
    LocalDateTime getFecha();
    String getCliente();
    String getItem();
    String getVendedor();
    BigDecimal getTotal();
}
