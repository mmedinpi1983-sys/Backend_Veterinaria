package com.utp.sistemaclinicaveterinaria.modulos.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Utilidad de auditoría: obtiene el id del empleado logueado a partir del header
// "X-Usuario-Id" que el frontend envía en cada petición (lo toma del usuario en localStorage).
// Si el header no viene o es inválido, cae a 1 (usuario sistema) para no romper la operación.
public final class UsuarioActual {

    private UsuarioActual() {}

    public static Integer getId() {
        try {
            ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                String header = attrs.getRequest().getHeader("X-Usuario-Id");
                if (header != null && !header.isBlank()) {
                    return Integer.valueOf(header.trim());
                }
            }
        } catch (Exception ignored) {
            // header ausente/ inválido o sin contexto de request -> usamos el fallback
        }
        return 1;
    }
}