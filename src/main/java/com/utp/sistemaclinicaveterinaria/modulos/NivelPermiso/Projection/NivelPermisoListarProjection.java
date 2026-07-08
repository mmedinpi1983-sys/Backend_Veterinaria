package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection;

import java.time.LocalDateTime;

public interface NivelPermisoListarProjection {
    Integer getIdNivelPermiso();
    Integer getIdPermiso();
    Integer getIdNivelSuscripcion();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
