package com.utp.sistemaclinicaveterinaria.modulos.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Deja un registro de cada peticion que entra al sistema (que se hizo, resultado y cuanto tardo).
// Con esto todos los modulos quedan "logueados" sin tener que tocar cada controlador.
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger("Auditoria");

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        long inicio = System.currentTimeMillis();
        try {
            chain.doFilter(req, res);
        } finally {
            // no ensuciamos el log con las peticiones de preflight del navegador
            if (!"OPTIONS".equalsIgnoreCase(req.getMethod())) {
                long ms = System.currentTimeMillis() - inicio;
                String idUsuario = req.getHeader("X-Usuario-Id");
                String usuario = (idUsuario == null || idUsuario.isBlank()) ? "anonimo" : "empleado#" + idUsuario;
                int status = res.getStatus();
                String linea = String.format("%s %s -> %d (%d ms) [usuario: %s]",
                        req.getMethod(), req.getRequestURI(), status, ms, usuario);

                if (status >= 500) {
                    log.error(linea);
                } else if (status >= 400) {
                    log.warn(linea);
                } else {
                    log.info(linea);
                }
            }
        }
    }
}
