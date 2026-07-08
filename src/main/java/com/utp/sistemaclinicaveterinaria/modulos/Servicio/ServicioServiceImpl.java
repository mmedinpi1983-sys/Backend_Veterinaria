package com.utp.sistemaclinicaveterinaria.modulos.Servicio;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository r;
    private final ServicioMapper m;

    public ServicioServiceImpl(ServicioRepository r, ServicioMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<ServicioCatalogResponse> catalogo(Integer idAsociado) {
        return m.ServicioCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<ServicioListResponse> listar(ServicioFilterRequest f) {
        return m.ServicioListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public ServicioDetailResponse obtenerId(Integer idServicio, Integer idAsociado) {
        return m.ServicioDetailMapper(r.detalle(idServicio, idAsociado));
    }

    @Override
    public void crear(ServicioCreateRequest c) {
        Servicio entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idServicio, ServicioUpdateRequest mt) {
        Servicio entity = r.getReferenceById(idServicio);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(ServicioDeleteRequest e) {
        r.eliminar(e.idServicio(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
