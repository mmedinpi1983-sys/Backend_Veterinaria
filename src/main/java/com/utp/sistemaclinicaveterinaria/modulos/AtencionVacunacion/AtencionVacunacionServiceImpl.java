package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class AtencionVacunacionServiceImpl implements AtencionVacunacionService {
    private final AtencionVacunacionRepository r;
    private final AtencionVacunacionMapper m;

    public AtencionVacunacionServiceImpl(AtencionVacunacionRepository r, AtencionVacunacionMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<AtencionVacunacionListResponse> listar() {
        return m.AtencionVacunacionListMapperList(r.listar());
    }

    @Override
    public AtencionVacunacionDetailResponse obtenerId(Integer idVacunacion) {
        return m.AtencionVacunacionDetailMapper(r.detalle(idVacunacion));
    }

    @Override
    public void crear(AtencionVacunacionCreateRequest c) {
        AtencionVacunacion entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idVacunacion, AtencionVacunacionUpdateRequest mt) {
        AtencionVacunacion entity = r.getReferenceById(idVacunacion);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AtencionVacunacionDeleteRequest e) {
        r.eliminar(e.idVacunacion(), UsuarioActual.getId());
    }
}
