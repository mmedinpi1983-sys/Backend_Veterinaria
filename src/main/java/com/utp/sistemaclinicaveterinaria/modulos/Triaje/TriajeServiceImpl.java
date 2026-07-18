package com.utp.sistemaclinicaveterinaria.modulos.Triaje;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class TriajeServiceImpl implements TriajeService {
    private final TriajeRepository r;
    private final TriajeMapper m;

    public TriajeServiceImpl(TriajeRepository r, TriajeMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<TriajeCatalogResponse> catalogo(Integer idAsociado) {
        return m.TriajeCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<TriajeListResponse> listar(TriajeFilterRequest f) {
        return m.TriajeListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public TriajeDetailResponse obtenerId(Integer idTriaje, Integer idAsociado) {
        return m.TriajeDetailMapper(r.detalle(idTriaje, idAsociado));
    }

    @Override
    public Integer crear(TriajeCreateRequest c) {
        Triaje entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        return r.save(entity).getIdTriaje();
    }

    @Override
    public void actualizar(Integer idTriaje, TriajeUpdateRequest mt) {
        Triaje entity = r.getReferenceById(idTriaje);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(TriajeDeleteRequest e) {
        r.eliminar(e.idTriaje(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
