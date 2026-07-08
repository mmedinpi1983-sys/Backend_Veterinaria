package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionUpdateRequest;

@Service
public class EstadoSalidaAtencionServiceImpl implements EstadoSalidaAtencionService {
    private final EstadoSalidaAtencionRepository r;
    private final EstadoSalidaAtencionMapper m;

    public EstadoSalidaAtencionServiceImpl(EstadoSalidaAtencionRepository r, EstadoSalidaAtencionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<EstadoSalidaAtencionCatalogResponse> catalogo() {
        return m.EstadoSalidaAtencionCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<EstadoSalidaAtencionListResponse> listar(EstadoSalidaAtencionFilterRequest f) {
        return m.EstadoSalidaAtencionListMapperList(r.listar(f.nombre()));
    }

    @Override
    public EstadoSalidaAtencionDetailResponse obtenerId(Integer id) {
        return m.EstadoSalidaAtencionDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(EstadoSalidaAtencionCreateRequest c) {
        EstadoSalidaAtencion entity = m.toEntity(c);
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, EstadoSalidaAtencionUpdateRequest mt) {
        EstadoSalidaAtencion entity = r.findById(id).orElseThrow(() -> new RuntimeException("EstadoSalidaAtencion no encontrado"));
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(EstadoSalidaAtencionDeleteRequest e) {
        r.eliminar(e.idEstadoSalida());
    }
}
