package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection;

import java.time.LocalDateTime;

public interface RolesClinicaListarProjection {
    Integer getIdRoles();
    String getNombreRol();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
