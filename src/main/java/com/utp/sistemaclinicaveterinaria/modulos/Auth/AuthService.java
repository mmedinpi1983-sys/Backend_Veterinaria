package com.utp.sistemaclinicaveterinaria.modulos.Auth;

public interface AuthService {
    AuthDTO.LoginResponse login(AuthDTO.LoginRequest request);
}
