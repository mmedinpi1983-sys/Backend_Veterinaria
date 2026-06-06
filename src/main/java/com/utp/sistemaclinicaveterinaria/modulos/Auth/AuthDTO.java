package com.utp.sistemaclinicaveterinaria.modulos.Auth;

public interface AuthDTO {
    record LoginRequest(String correo, String contrasena) {}
    record LoginResponse(
        Integer idEmpleadoAsociado,
        String nombreEmpleado,
        String apellidoPaterno,
        String correo,
        Integer idRolesClinica,
        Integer idAsociado
    ) {}
}
