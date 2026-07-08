package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class AtencionEsteticaServiceImpl implements AtencionEsteticaService {
    private final AtencionEsteticaRepository r;
    private final AtencionEsteticaMapper m;

    public AtencionEsteticaServiceImpl(AtencionEsteticaRepository r, AtencionEsteticaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<AtencionEsteticaListResponse> listar() {
        return m.AtencionEsteticaListMapperList(r.listar());
    }

    @Override
    public AtencionEsteticaDetailResponse obtenerId(Integer idEstetica) {
        return m.AtencionEsteticaDetailMapper(r.detalle(idEstetica));
    }

    @Override
    public void crear(AtencionEsteticaCreateRequest c) {
        AtencionEstetica entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idEstetica, AtencionEsteticaUpdateRequest mt) {
        AtencionEstetica entity = r.getReferenceById(idEstetica);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AtencionEsteticaDeleteRequest e) {
        r.eliminar(e.idEstetica(), UsuarioActual.getId());
    }
}
