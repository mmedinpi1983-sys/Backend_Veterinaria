package com.utp.sistemaclinicaveterinaria.modulos.Auth;

import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoRepository;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;

// Implementación del servicio de autenticación
@Service
public class AuthServiceImpl implements AuthService {

    private final EmpleadoAsociadoRepository empleadoRepo;

    public AuthServiceImpl(EmpleadoAsociadoRepository empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    // Busca un empleado activo que coincida con el correo y contraseña ingresados
    @Override
    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest request) {
        return empleadoRepo.findByFechaEliminacionIsNull().stream()
            .filter(e -> e.getCorreo().equalsIgnoreCase(request.correo())
                      && e.getContrasena().equals(request.contrasena())
                      && Boolean.TRUE.equals(e.getEstado()))
            .findFirst()
            .map(e -> new AuthDTO.LoginResponse(
                e.getIdEmpleadoAsociado(),
                e.getNombreEmpleado(),
                e.getApellidoPaterno(),
                e.getCorreo(),
                e.getIdRolesClinica(),
                e.getIdAsociado()
            ))
            .orElseThrow(() -> new ApiException("Credenciales incorrectas", "UNAUTHORIZED"));
    }
}
