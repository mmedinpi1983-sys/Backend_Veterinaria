package com.utp.sistemaclinicaveterinaria.modulos.Dueno;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class DuenoServiceImpl implements DuenoService {
    private final DuenoRepository r;
    private final DuenoMapper m;

    public DuenoServiceImpl(DuenoRepository r, DuenoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<DuenoCatalogResponse> catalogo(Integer idAsociado) {
        return m.DuenoCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<DuenoListResponse> listar(DuenoFilterRequest f) {
        return m.DuenoListMapperList(r.listar(UsuarioActual.getAsociadoId(), f.estado(), f.q()));
    }

    @Override
    public DuenoDetailResponse obtenerId(Integer idDueno, Integer idAsociado) {
        return m.DuenoDetailMapper(r.detalle(idDueno, idAsociado));
    }

    @Override
    public void crear(DuenoCreateRequest c) {
        if (r.existsByNroDocumentoAndFechaEliminacionIsNull(c.nroDocumento())) {
            throw new ApiException("Ya existe un dueño registrado con ese DNI", "DUPLICATE");
        }
        Dueno entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idDueno, DuenoUpdateRequest u) {
        if (r.existsByNroDocumentoAndIdDuenoNotAndFechaEliminacionIsNull(u.nroDocumento(), idDueno)) {
            throw new ApiException("Ya existe otro dueño registrado con ese DNI", "DUPLICATE");
        }
        Dueno entity = r.getReferenceById(idDueno);
        m.updateEntity(entity, u);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(DuenoDeleteRequest e) {
        r.eliminar(e.idDueno(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
