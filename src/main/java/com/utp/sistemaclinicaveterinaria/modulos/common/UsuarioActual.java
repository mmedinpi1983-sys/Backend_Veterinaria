package com.utp.sistemaclinicaveterinaria.modulos.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.utp.sistemaclinicaveterinaria.config.UserPrincipal;

public final class UsuarioActual {

    private UsuarioActual() {
    }

    public static Integer getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal up) {
            return up.idEmpleado();
        }
        return 1;
    }

    public static Integer getAsociadoId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal up) {
            return up.idAsociado();
        }
        return 1;
    }

    public static String getNombreCompleto() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal up) {
            return up.nombreCompleto();
        }
        return "Sistema";
    }

}
