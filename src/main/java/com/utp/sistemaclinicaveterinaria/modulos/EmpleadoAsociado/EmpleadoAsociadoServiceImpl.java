package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class EmpleadoAsociadoServiceImpl implements EmpleadoAsociadoService {
    private final EmpleadoAsociadoRepository r;
    private final EmpleadoAsociadoMapper m;
    private final PasswordEncoder passwordEncoder;

    public EmpleadoAsociadoServiceImpl(EmpleadoAsociadoRepository r, EmpleadoAsociadoMapper m, PasswordEncoder passwordEncoder) {
        this.r = r;
        this.m = m;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<EmpleadoAsociadoCatalogResponse> catalogo(Integer idAsociado) {
        return m.EmpleadoAsociadoCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<EmpleadoAsociadoListResponse> listar(EmpleadoAsociadoFilterRequest f) {
        return m.EmpleadoAsociadoListMapperList(r.listar(UsuarioActual.getAsociadoId(), f.estado(), f.q()));
    }

    @Override
    public EmpleadoAsociadoDetailResponse obtenerId(Integer idEmpleadoAsociado, Integer idAsociado) {
        return m.EmpleadoAsociadoDetailMapper(r.detalle(idEmpleadoAsociado, idAsociado));
    }

    @Override
    public void crear(EmpleadoAsociadoCreateRequest c) {
        EmpleadoAsociado entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setContrasena(passwordEncoder.encode(c.contrasena()));
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idEmpleadoAsociado, EmpleadoAsociadoUpdateRequest u) {
        EmpleadoAsociado entity = r.getReferenceById(idEmpleadoAsociado);
        if (u.contrasena() != null && !u.contrasena().isBlank()) {
            entity.setContrasena(passwordEncoder.encode(u.contrasena()));
        }
        m.updateEntity(entity, u);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(EmpleadoAsociadoDeleteRequest e) {
        r.eliminar(e.idEmpleadoAsociado(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
