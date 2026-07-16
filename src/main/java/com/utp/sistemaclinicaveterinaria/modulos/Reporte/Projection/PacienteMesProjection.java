package com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection;
import java.time.LocalDate;
public interface PacienteMesProjection {
    LocalDate getMes();
    Integer getCantidad();
}
