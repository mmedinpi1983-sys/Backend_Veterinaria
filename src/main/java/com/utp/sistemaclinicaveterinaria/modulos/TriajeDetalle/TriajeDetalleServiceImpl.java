package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class TriajeDetalleServiceImpl implements TriajeDetalleService {
    private final TriajeDetalleRepository r;
    private final TriajeDetalleMapper m;

    public TriajeDetalleServiceImpl(TriajeDetalleRepository r, TriajeDetalleMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<TriajeDetalleCatalogResponse> catalogo() {
        return m.TriajeDetalleCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<TriajeDetalleListResponse> listar(TriajeDetalleFilterRequest f) {
        return m.TriajeDetalleListMapperList(r.listar());
    }

    @Override
    public TriajeDetalleDetailResponse obtenerId(Integer idTriajeDetalle) {
        return m.TriajeDetalleDetailMapper(r.detalle(idTriajeDetalle));
    }

    @Override
    public void crear(TriajeDetalleCreateRequest c) {
        TriajeDetalle entity = m.toEntity(c);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idTriajeDetalle, TriajeDetalleUpdateRequest mt) {
        TriajeDetalle entity = r.getReferenceById(idTriajeDetalle);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(TriajeDetalleDeleteRequest e) {
        r.eliminar(e.idTriajeDetalle(), UsuarioActual.getId());
    }
}
