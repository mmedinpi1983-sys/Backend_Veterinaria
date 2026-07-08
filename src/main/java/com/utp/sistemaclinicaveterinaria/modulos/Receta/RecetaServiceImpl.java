package com.utp.sistemaclinicaveterinaria.modulos.Receta;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaUpdateRequest;

@Service
public class RecetaServiceImpl implements RecetaService {
    private final RecetaRepository r;
    private final RecetaMapper m;

    public RecetaServiceImpl(RecetaRepository r, RecetaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<RecetaCatalogResponse> catalogo(Integer idAsociado) {
        return m.toCatalogResponseList(r.catalogo(idAsociado));
    }

    @Override
    public List<RecetaListResponse> listar() {
        return m.toListResponseList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public RecetaDetailResponse obtenerId(Integer id, Integer idAsociado) {
        return m.toDetailResponse(r.detalle(id, idAsociado));
    }

    @Override
    public void crear(RecetaCreateRequest c) {
        Receta entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, RecetaUpdateRequest t) {
        Receta entity = r.getReferenceById(id);
        m.updateEntity(entity, t);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(RecetaDeleteRequest e) {
        r.eliminar(e.idReceta(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
