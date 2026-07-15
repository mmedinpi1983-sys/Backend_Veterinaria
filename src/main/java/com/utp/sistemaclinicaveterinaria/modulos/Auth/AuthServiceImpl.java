package com.utp.sistemaclinicaveterinaria.modulos.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.config.JwtUtil;
import com.utp.sistemaclinicaveterinaria.modulos.Auth.AuthDTO.LoginRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Auth.AuthDTO.LoginResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociado;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoRepository;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;

// Implementación del servicio de autenticación
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final EmpleadoAsociadoRepository empleadoRepo;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(EmpleadoAsociadoRepository empleadoRepo, JwtUtil jwtUyUtil,
            PasswordEncoder passwordEncoder) {
        this.empleadoRepo = empleadoRepo;
        this.jwtUtil = jwtUyUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // Busca un empleado que coincida con el correo y contraseña ingresados, y valida que esté activo
    @Override
    public LoginResponse login(LoginRequest request) {
        EmpleadoAsociado empleado = empleadoRepo.findByFechaEliminacionIsNull().stream()
                .filter(e -> e.getCorreo().equalsIgnoreCase(request.correo()))
                .findFirst()
                .orElseThrow(() -> new ApiException("Credenciales incorrectas", "UNAUTHORIZED"));

        if (!passwordEncoder.matches(request.contrasena(), empleado.getContrasena())) {
            throw new ApiException("Credenciales incorrectas", "UNAUTHORIZED");
        }

        if (!Boolean.TRUE.equals(empleado.getEstado())) {
            throw new ApiException("Cuenta inactiva", "ACCOUNT_INACTIVE");
        }

        String nombreCompleto = empleado.getNombreEmpleado() + " " + empleado.getApellidoPaterno();
        String token = jwtUtil.generateToken(
                empleado.getIdEmpleadoAsociado(),
                empleado.getIdAsociado(),
                nombreCompleto,
                empleado.getIdRolesClinica());
        return new AuthDTO.LoginResponse(
                empleado.getIdEmpleadoAsociado(),
                empleado.getNombreEmpleado(),
                empleado.getApellidoPaterno(),
                empleado.getCorreo(),
                empleado.getIdRolesClinica(),
                empleado.getIdAsociado(),
                token);
    }
}
