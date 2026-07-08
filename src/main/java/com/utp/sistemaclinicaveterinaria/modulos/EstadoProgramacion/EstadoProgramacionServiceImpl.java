package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class EstadoProgramacionServiceImpl implements EstadoProgramacionService {
    private final EstadoProgramacionRepository r;
    private final EstadoProgramacionMapper m;

    public EstadoProgramacionServiceImpl(EstadoProgramacionRepository r, EstadoProgramacionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<EstadoProgramacionCatalogResponse> catalogo() {
        return m.EstadoProgramacionCatalogoMapperList(r.catalogo(UsuarioActual.getAsociadoId()));
    }

    @Override
    public List<EstadoProgramacionListResponse> listar(EstadoProgramacionFilterRequest f) {
        return m.EstadoProgramacionListMapperList(r.listar(UsuarioActual.getAsociadoId(), f.estado(), f.nombre()));
    }

    @Override
    public EstadoProgramacionDetailResponse obtenerId(Integer id) {
        return m.EstadoProgramacionDetailMapper(r.detalle(id, UsuarioActual.getAsociadoId()));
    }

    @Override
    public void crear(EstadoProgramacionCreateRequest c) {
        EstadoProgramacion entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, EstadoProgramacionUpdateRequest mt) {
        EstadoProgramacion entity = r.findById(id).orElseThrow(() -> new RuntimeException("EstadoProgramacion no encontrado"));
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(EstadoProgramacionDeleteRequest e) {
        r.eliminar(e.idEstadoProgramacion(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
