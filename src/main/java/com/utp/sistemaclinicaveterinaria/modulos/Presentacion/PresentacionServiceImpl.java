package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class PresentacionServiceImpl implements PresentacionService {
    private final PresentacionRepository r;
    private final PresentacionMapper m;

    public PresentacionServiceImpl(PresentacionRepository r, PresentacionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<PresentacionCatalogResponse> catalogo(Integer idAsociado) {
        return m.PresentacionCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<PresentacionListResponse> listar(PresentacionFilterRequest f) {
        return m.PresentacionListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public PresentacionDetailResponse obtenerId(Integer idPresentacion, Integer idAsociado) {
        return m.PresentacionDetailMapper(r.detalle(idPresentacion, idAsociado));
    }

    @Override
    public void crear(PresentacionCreateRequest c) {
        Presentacion entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idPresentacion, PresentacionUpdateRequest mt) {
        Presentacion entity = r.getReferenceById(idPresentacion);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(PresentacionDeleteRequest e) {
        r.eliminar(e.idPresentacion(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
