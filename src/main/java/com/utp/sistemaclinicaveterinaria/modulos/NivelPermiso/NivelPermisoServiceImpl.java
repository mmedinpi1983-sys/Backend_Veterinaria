package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class NivelPermisoServiceImpl implements NivelPermisoService {
    private final NivelPermisoRepository r;
    private final NivelPermisoMapper m;

    public NivelPermisoServiceImpl(NivelPermisoRepository r, NivelPermisoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<NivelPermisoCatalogResponse> catalogo() {
        return m.NivelPermisoCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<NivelPermisoListResponse> listar() {
        return m.NivelPermisoListMapperList(r.listar());
    }

    @Override
    public NivelPermisoDetailResponse obtenerId(Integer id) {
        return m.NivelPermisoDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(NivelPermisoCreateRequest c) {
        NivelPermiso entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdSuperAdminCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, NivelPermisoUpdateRequest mt) {
        NivelPermiso entity = r.getReferenceById(id);
        m.updateEntity(entity, mt);
        entity.setIdSuperAdminModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(NivelPermisoDeleteRequest e) {
        r.eliminar(e.idNivelPermiso(), UsuarioActual.getId());
    }
}
