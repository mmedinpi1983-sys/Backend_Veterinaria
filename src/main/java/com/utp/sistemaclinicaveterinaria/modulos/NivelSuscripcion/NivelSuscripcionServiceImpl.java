package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class NivelSuscripcionServiceImpl implements NivelSuscripcionService {
    private final NivelSuscripcionRepository r;
    private final NivelSuscripcionMapper m;

    public NivelSuscripcionServiceImpl(NivelSuscripcionRepository r, NivelSuscripcionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<NivelSuscripcionCatalogResponse> catalogo() {
        return m.NivelSuscripcionCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<NivelSuscripcionListResponse> listar() {
        return m.NivelSuscripcionListMapperList(r.listar());
    }

    @Override
    public NivelSuscripcionDetailResponse obtenerId(Integer id) {
        return m.NivelSuscripcionDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(NivelSuscripcionCreateRequest c) {
        NivelSuscripcion entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdSuperAdminCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, NivelSuscripcionUpdateRequest mt) {
        NivelSuscripcion entity = r.getReferenceById(id);
        m.updateEntity(entity, mt);
        entity.setIdSuperAdminModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(NivelSuscripcionDeleteRequest e) {
        r.eliminar(e.idNivel(), UsuarioActual.getId());
    }
}
