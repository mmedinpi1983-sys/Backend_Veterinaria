package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleUpdateRequest;

@Service
public class RecetaDetalleServiceImpl implements RecetaDetalleService {
    private final RecetaDetalleRepository r;
    private final RecetaDetalleMapper m;

    public RecetaDetalleServiceImpl(RecetaDetalleRepository r, RecetaDetalleMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<RecetaDetalleCatalogResponse> catalogo() {
        return m.toCatalogResponseList(r.catalogo());
    }

    @Override
    public List<RecetaDetalleListResponse> listar() {
        return m.toListResponseList(r.listar());
    }

    @Override
    public RecetaDetalleDetailResponse obtenerId(Integer id) {
        return m.toDetailResponse(r.detalle(id));
    }

    @Override
    public void crear(RecetaDetalleCreateRequest c) {
        RecetaDetalle entity = m.toEntity(c);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, RecetaDetalleUpdateRequest t) {
        RecetaDetalle entity = r.getReferenceById(id);
        m.updateEntity(entity, t);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(RecetaDetalleDeleteRequest e) {
        r.eliminar(e.idRecetaDetalle(), UsuarioActual.getId());
    }
}
