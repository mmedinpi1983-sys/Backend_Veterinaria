package com.utp.sistemaclinicaveterinaria.modulos.Permiso;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class PermisoServiceImpl implements PermisoService {
    private final PermisoRepository r;
    private final PermisoMapper m;

    public PermisoServiceImpl(PermisoRepository r, PermisoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<PermisoCatalogResponse> catalogo(Integer idAsociado) {
        return m.PermisoCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<PermisoListResponse> listar(PermisoFilterRequest f) {
        return m.PermisoListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public PermisoDetailResponse obtenerId(Integer idPermiso, Integer idAsociado) {
        return m.PermisoDetailMapper(r.detalle(idPermiso, idAsociado));
    }

    @Override
    public void crear(PermisoCreateRequest c) {
        Permiso entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idPermiso, PermisoUpdateRequest mt) {
        Permiso entity = r.getReferenceById(idPermiso);
        m.updateEntity(entity, mt);
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(PermisoDeleteRequest e) {
        r.eliminar(e.idPermiso(), UsuarioActual.getAsociadoId());
    }
}
