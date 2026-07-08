package com.utp.sistemaclinicaveterinaria.modulos.Turno;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class TurnoServiceImpl implements TurnoService {
    private final TurnoRepository r;
    private final TurnoMapper m;

    public TurnoServiceImpl(TurnoRepository r, TurnoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<TurnoCatalogResponse> catalogo(Integer idASociado) {
        return m.TurnoCatalogoMapperList(r.catalogo(idASociado));
    }

    @Override
    public List<TurnoListResponse> listar(TurnoFilterRequest f) {
        return m.TurnoListMapperList(r.listar(f.nombre(), f.estado(), UsuarioActual.getAsociadoId()));
    }

    @Override
    public TurnoDetailResponse obtenerId(Integer idTurno, Integer idAsociado) {
        return m.TurnoDetailMapper(r.detalle(idTurno, idAsociado));
    }

    @Override
    public void crear(TurnoCreateRequest c) {
        Turno entity = m.toEntity(c);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity = r.save(entity);
    }

    @Override
    public void actualizar(Integer idTurno, TurnoUpdateRequest mt) {
        Turno entity = r.getReferenceById(idTurno);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(TurnoDeleteRequest e) {
        r.eliminar(e.idTurno(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }

}
