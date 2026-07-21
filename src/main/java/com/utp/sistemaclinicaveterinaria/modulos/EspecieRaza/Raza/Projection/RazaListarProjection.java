package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.Projection;

import java.time.LocalDateTime;

public interface RazaListarProjection {
    Integer getIdEspecieRaza();
    String getNombre();
    String getNombreEspecie();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
