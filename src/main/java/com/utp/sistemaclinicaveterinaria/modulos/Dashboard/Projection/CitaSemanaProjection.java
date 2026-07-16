package com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection;

import java.time.LocalDate;

public interface CitaSemanaProjection {
    LocalDate getDia();
    Integer getCantidad();
}
