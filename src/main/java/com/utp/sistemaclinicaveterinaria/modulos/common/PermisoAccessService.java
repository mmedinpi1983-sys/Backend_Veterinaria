package com.utp.sistemaclinicaveterinaria.modulos.common;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoRepository;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

// Resuelve a qué módulos tiene acceso un rol, a partir de los permisos otorgados en Rol_Permiso.
// El rol con el permiso "SuperAdmin" tiene acceso a todo (bypass total).
// Tener acceso a "atencion clinica" implica también acceso a "citas": hoy la única forma de llegar
// a una atención es abriendo la cita y pulsando "Iniciar Atención".
@Service
public class PermisoAccessService {
    private static final Pattern DIACRITICOS = Pattern.compile("\\p{M}");

    private final RolPermisoRepository rolPermisoRepository;

    public PermisoAccessService(RolPermisoRepository rolPermisoRepository) {
        this.rolPermisoRepository = rolPermisoRepository;
    }

    public static String normalizar(String texto) {
        if (texto == null) return "";
        String sinAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return DIACRITICOS.matcher(sinAcentos).replaceAll("").trim().toLowerCase();
    }

    public record AccesoRol(boolean superAdmin, Set<String> modulos) {
        public boolean tieneModulo(String modulo) {
            return superAdmin || modulos.contains(normalizar(modulo));
        }
    }

    public AccesoRol resolver(Integer idRolesClinica, Integer idAsociado) {
        if (idRolesClinica == null) {
            return new AccesoRol(false, Set.of());
        }
        Set<String> nombres = new HashSet<>();
        for (String nombre : rolPermisoRepository.nombrePermisosDeRol(idRolesClinica, idAsociado)) {
            nombres.add(normalizar(nombre));
        }
        boolean superAdmin = nombres.contains("superadmin");
        if (nombres.contains("atencion clinica")) {
            nombres.add("citas");
        }
        return new AccesoRol(superAdmin, nombres);
    }

    public boolean tieneAcceso(Integer idRolesClinica, Integer idAsociado, String moduloNormalizado) {
        return resolver(idRolesClinica, idAsociado).tieneModulo(moduloNormalizado);
    }
}
