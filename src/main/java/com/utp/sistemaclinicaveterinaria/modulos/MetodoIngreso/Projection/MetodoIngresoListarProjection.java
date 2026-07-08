package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection;

public interface MetodoIngresoListarProjection {
    Integer getIdMetodoIngreso();
    String getNombre();
    String getDescripcion();
    Boolean getEstado();
}
