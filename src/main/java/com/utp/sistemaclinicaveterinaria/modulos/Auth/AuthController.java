package com.utp.sistemaclinicaveterinaria.modulos.Auth;

import org.springframework.web.bind.annotation.*;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;
import jakarta.validation.Valid;

// Controlador de autenticación - maneja el inicio de sesión del sistema
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) { this.service = service; }

    // POST /api/auth/login - valida credenciales y retorna datos del empleado
    @PostMapping("/login")
    public ApiResponse<AuthDTO.LoginResponse> login(@Valid @RequestBody AuthDTO.LoginRequest request) {
        return ApiResponse.ResponseAn("login", service.login(request));
    }
}
