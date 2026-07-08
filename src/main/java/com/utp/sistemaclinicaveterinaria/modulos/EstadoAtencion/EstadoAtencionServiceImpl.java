package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionUpdateRequest;

@Service
public class EstadoAtencionServiceImpl implements EstadoAtencionService {
    private final EstadoAtencionRepository r;
    private final EstadoAtencionMapper m;

    public EstadoAtencionServiceImpl(EstadoAtencionRepository r, EstadoAtencionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<EstadoAtencionCatalogResponse> catalogo() {
        return m.EstadoAtencionCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<EstadoAtencionListResponse> listar(EstadoAtencionFilterRequest f) {
        return m.EstadoAtencionListMapperList(r.listar(f.nombre()));
    }

    @Override
    public EstadoAtencionDetailResponse obtenerId(Integer id) {
        return m.EstadoAtencionDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(EstadoAtencionCreateRequest c) {
        EstadoAtencion entity = m.toEntity(c);
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, EstadoAtencionUpdateRequest mt) {
        EstadoAtencion entity = r.findById(id).orElseThrow(() -> new RuntimeException("EstadoAtencion no encontrado"));
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(EstadoAtencionDeleteRequest e) {
        r.eliminar(e.idEstadoAtencion());
    }
}
