package com.utp.sistemaclinicaveterinaria.config;

public record UserPrincipal(
        Integer idEmpleado,
        Integer idAsociado,
        String nombreCompleto,
        Integer idRolesClinica
) {
}
