package com.utp.sistemaclinicaveterinaria.config;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.sistemaclinicaveterinaria.modulos.common.PermisoAccessService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Aplica la matriz de "Permisos por Perfil" (Rol_Permiso) sobre las rutas de la API.
// Solo actúa cuando el request ya está autenticado (JwtAuthenticationFilter corrió antes);
// las rutas públicas (/api/auth/**, swagger) y las que no aparecen en MODULOS_GATEADOS
// (catálogos de referencia compartidos entre módulos) quedan abiertas a cualquier empleado logueado.
@Component
@Order(2)
public class PermisoAuthorizationFilter extends OncePerRequestFilter {
    private final PermisoAccessService permisoAccessService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // prefijo de la API -> módulo dueño. El primer match por prefijo gana.
    private static final Map<String, String> MODULOS_GATEADOS = new LinkedHashMap<>();
    static {
        MODULOS_GATEADOS.put("/api/mascota", "pacientes");
        MODULOS_GATEADOS.put("/api/dueno", "pacientes");
        MODULOS_GATEADOS.put("/api/duenomascota", "pacientes");

        MODULOS_GATEADOS.put("/api/citaprogramada", "citas");

        MODULOS_GATEADOS.put("/api/programacion", "programacion");
        MODULOS_GATEADOS.put("/api/turno", "programacion");
        MODULOS_GATEADOS.put("/api/consultorio", "programacion");
        MODULOS_GATEADOS.put("/api/estadoprogramacion", "programacion");

        MODULOS_GATEADOS.put("/api/atencionconsulta", "atencion clinica");
        MODULOS_GATEADOS.put("/api/atencionestetica", "atencion clinica");
        MODULOS_GATEADOS.put("/api/atencionvacunacion", "atencion clinica");
        MODULOS_GATEADOS.put("/api/atencion", "atencion clinica");
        MODULOS_GATEADOS.put("/api/triajedetalle", "atencion clinica");
        MODULOS_GATEADOS.put("/api/triaje", "atencion clinica");
        MODULOS_GATEADOS.put("/api/anamnesis", "atencion clinica");
        MODULOS_GATEADOS.put("/api/consultadiagnostico", "atencion clinica");
        MODULOS_GATEADOS.put("/api/recetadetalle", "atencion clinica");
        MODULOS_GATEADOS.put("/api/receta", "atencion clinica");

        MODULOS_GATEADOS.put("/api/asociado", "configuracion");
        MODULOS_GATEADOS.put("/api/empleadoasociado", "configuracion");
        MODULOS_GATEADOS.put("/api/rolesclinica", "configuracion");
        MODULOS_GATEADOS.put("/api/permiso", "configuracion");
        MODULOS_GATEADOS.put("/api/rolpermiso", "configuracion");
        MODULOS_GATEADOS.put("/api/configuracionsistema", "configuracion");
    }

    // Catálogos que solo se restringen en escritura (Atención Clínica necesita leerlos para prescribir).
    private static final List<String> CATALOGOS_LECTURA_LIBRE = List.of("/api/medicamentocatalogo", "/api/diagnosticocatalogo");

    public PermisoAuthorizationFilter(PermisoAccessService permisoAccessService) {
        this.permisoAccessService = permisoAccessService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth != null && auth.getPrincipal() instanceof UserPrincipal principal)) {
            chain.doFilter(request, response);
            return;
        }

        String path = request.getServletPath();
        String modulo = resolverModulo(path, request.getMethod());
        if (modulo == null) {
            chain.doFilter(request, response);
            return;
        }

        boolean permitido = permisoAccessService.tieneAcceso(principal.idRolesClinica(), principal.idAsociado(), modulo);
        if (!permitido) {
            denegar(response);
            return;
        }
        chain.doFilter(request, response);
    }

    private String resolverModulo(String path, String method) {
        // Cualquier empleado autenticado necesita poder consultar sus propios módulos permitidos
        // (si no, un rol sin acceso a Configuración nunca podría ni preguntar qué sí puede ver).
        if (path.equals("/api/rolpermiso/mismodulos")) {
            return null;
        }
        // El topbar (visible en cualquier página) necesita poder mostrar el nombre del rol de
        // cualquier empleado logueado, sin importar si tiene acceso a Configuración o no.
        if (path.equals("/api/rolesclinica/catalogo") && "GET".equalsIgnoreCase(method)) {
            return null;
        }
        // Programación necesita listar empleados para el selector de veterinario, aunque el rol
        // no tenga acceso a Configuración (igual criterio que rolesclinica/catalogo arriba).
        if (path.equals("/api/empleadoasociado/catalogo") && "GET".equalsIgnoreCase(method)) {
            return null;
        }
        for (String prefijo : CATALOGOS_LECTURA_LIBRE) {
            if (path.startsWith(prefijo)) {
                return "GET".equalsIgnoreCase(method) ? null : "configuracion";
            }
        }
        for (var entry : MODULOS_GATEADOS.entrySet()) {
            if (path.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void denegar(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> body = Map.of(
                "success", false,
                "message", "No tienes permiso para acceder a este módulo",
                "errorCode", "FORBIDDEN");
        objectMapper.writeValue(response.getWriter(), body);
    }
}
