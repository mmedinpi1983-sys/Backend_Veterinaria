package com.utp.sistemaclinicaveterinaria.modulos.Categoria;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository r;
    private final CategoriaMapper m;

    public CategoriaServiceImpl(CategoriaRepository r, CategoriaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<CategoriaCatalogResponse> catalogo(Integer idAsociado) {
        return m.CategoriaCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<CategoriaListResponse> listar(CategoriaFilterRequest f) {
        return m.CategoriaListMapperList(r.listar(UsuarioActual.getAsociadoId(), f.estado(), f.nombreCategoria()));
    }

    @Override
    public CategoriaDetailResponse obtenerId(Integer idCategoria, Integer idAsociado) {
        return m.CategoriaDetailMapper(r.detalle(idCategoria, idAsociado));
    }

    @Override
    public void crear(CategoriaCreateRequest c) {
        Categoria entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idCategoria, CategoriaUpdateRequest mt) {
        Categoria entity = r.getReferenceById(idCategoria);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(CategoriaDeleteRequest e) {
        r.eliminar(e.idCategoria(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
