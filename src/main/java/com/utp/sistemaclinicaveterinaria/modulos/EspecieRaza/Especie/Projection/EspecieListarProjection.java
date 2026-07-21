package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.Projection;

import java.time.LocalDateTime;

public interface EspecieListarProjection {
    Integer getIdEspecieRaza();
    String getNombre();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
