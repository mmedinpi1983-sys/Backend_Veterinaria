package com.utp.sistemaclinicaveterinaria.modulos.Auth;

import jakarta.validation.constraints.NotBlank;

public interface AuthDTO {
    record LoginRequest(
        @NotBlank(message = "El correo es obligatorio") String correo,
        @NotBlank(message = "La contraseña es obligatoria") String contrasena
    ) {}
    record LoginResponse(
        Integer idEmpleadoAsociado,
        String nombreEmpleado,
        String apellidoPaterno,
        String correo,
        Integer idRolesClinica,
        Integer idAsociado
    ) {}
}
