package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class RolesClinicaServiceImpl implements RolesClinicaService {
    private final RolesClinicaRepository r;
    private final RolesClinicaMapper m;

    public RolesClinicaServiceImpl(RolesClinicaRepository r, RolesClinicaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<RolesClinicaCatalogResponse> catalogo(Integer idAsociado) {
        return m.RolesClinicaCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<RolesClinicaListResponse> listar(RolesClinicaFilterRequest f) {
        return m.RolesClinicaListMapperList(r.listar(UsuarioActual.getAsociadoId()));
    }

    @Override
    public RolesClinicaDetailResponse obtenerId(Integer idRoles, Integer idAsociado) {
        return m.RolesClinicaDetailMapper(r.detalle(idRoles, idAsociado));
    }

    @Override
    public void crear(RolesClinicaCreateRequest c) {
        RolesClinica entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idRoles, RolesClinicaUpdateRequest mt) {
        RolesClinica entity = r.getReferenceById(idRoles);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(RolesClinicaDeleteRequest e) {
        r.eliminar(e.idRoles(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
