package com.utp.sistemaclinicaveterinaria.modulos.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.config.JwtUtil;
import com.utp.sistemaclinicaveterinaria.modulos.Auth.AuthDTO.LoginRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Auth.AuthDTO.LoginResponse;
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

    // Busca un empleado activo que coincida con el correo y contraseña ingresados
    @Override
    public LoginResponse login(LoginRequest request) {
        return empleadoRepo.findByFechaEliminacionIsNull().stream()
                .filter(e -> e.getCorreo().equalsIgnoreCase(request.correo())
                        && passwordEncoder.matches(request.contrasena(), e.getContrasena())
                        && Boolean.TRUE.equals(e.getEstado()))
                .findFirst()
                .map(e -> {
                    String nombreCompleto = e.getNombreEmpleado() + " " + e.getApellidoPaterno();
                    String token = jwtUtil.generateToken(
                            e.getIdEmpleadoAsociado(),
                            e.getIdAsociado(),
                            nombreCompleto);
                    return new AuthDTO.LoginResponse(
                            e.getIdEmpleadoAsociado(),
                            e.getNombreEmpleado(),
                            e.getApellidoPaterno(),
                            e.getCorreo(),
                            e.getIdRolesClinica(),
                            e.getIdAsociado(),
                            token);
                }).orElseThrow(() -> new ApiException("Credenciales incorrectas", "UNAUTHORIZED"));
    }
}
