package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class RolPermisoServiceImpl implements RolPermisoService {
    private final RolPermisoRepository r;
    private final RolPermisoMapper m;

    public RolPermisoServiceImpl(RolPermisoRepository r, RolPermisoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<RolPermisoCatalogResponse> catalogo(Integer idAsociado) {
        return m.RolPermisoCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<RolPermisoListResponse> listar(RolPermisoFilterRequest f) {
        return m.RolPermisoListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public RolPermisoDetailResponse obtenerId(Integer idRolPermiso, Integer idAsociado) {
        return m.RolPermisoDetailMapper(r.detalle(idRolPermiso, idAsociado));
    }

    @Override
    public void crear(RolPermisoCreateRequest c) {
        RolPermiso entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idRolPermiso, RolPermisoUpdateRequest mt) {
        RolPermiso entity = r.getReferenceById(idRolPermiso);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(RolPermisoDeleteRequest e) {
        r.eliminar(e.idRolPermiso(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
