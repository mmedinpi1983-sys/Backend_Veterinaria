package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaUpdateRequest;

@Service
public class EstadoCitaServiceImpl implements EstadoCitaService {
    private final EstadoCitaRepository r;
    private final EstadoCitaMapper m;

    public EstadoCitaServiceImpl(EstadoCitaRepository r, EstadoCitaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<EstadoCitaCatalogResponse> catalogo() {
        return m.EstadoCitaCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<EstadoCitaListResponse> listar(EstadoCitaFilterRequest f) {
        return m.EstadoCitaListMapperList(r.listar(f.nombre()));
    }

    @Override
    public EstadoCitaDetailResponse obtenerId(Integer id) {
        return m.EstadoCitaDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(EstadoCitaCreateRequest c) {
        EstadoCita entity = m.toEntity(c);
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, EstadoCitaUpdateRequest mt) {
        EstadoCita entity = r.findById(id).orElseThrow(() -> new RuntimeException("EstadoCita no encontrado"));
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(EstadoCitaDeleteRequest e) {
        r.eliminar(e.idEstadoCita());
    }
}
