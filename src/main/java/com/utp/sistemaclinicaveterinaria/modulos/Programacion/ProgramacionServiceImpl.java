package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionUpdateRequest;

@Service
public class ProgramacionServiceImpl implements ProgramacionService {
    private final ProgramacionRepository r;
    private final ProgramacionMapper m;

    public ProgramacionServiceImpl(ProgramacionRepository r, ProgramacionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<ProgramacionCatalogResponse> catalogo(Integer idAsociado) {
        return m.toCatalogResponseList(r.catalogo(idAsociado));
    }

    @Override
    public List<ProgramacionListResponse> listar() {
        return m.toListResponseList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public ProgramacionDetailResponse obtenerId(Integer id, Integer idAsociado) {
        return m.toDetailResponse(r.detalle(id, idAsociado));
    }

    @Override
    public void crear(ProgramacionCreateRequest c) {
        Programacion entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, ProgramacionUpdateRequest t) {
        Programacion entity = r.getReferenceById(id);
        m.updateEntity(entity, t);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(ProgramacionDeleteRequest e) {
        r.eliminar(e.idProgramacion(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
