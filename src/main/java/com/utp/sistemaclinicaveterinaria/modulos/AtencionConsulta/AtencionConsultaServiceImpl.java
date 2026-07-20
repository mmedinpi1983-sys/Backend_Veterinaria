package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class AtencionConsultaServiceImpl implements AtencionConsultaService {
    private final AtencionConsultaRepository r;
    private final AtencionConsultaMapper m;

    public AtencionConsultaServiceImpl(AtencionConsultaRepository r, AtencionConsultaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<AtencionConsultaListResponse> listar() {
        return m.AtencionConsultaListMapperList(r.listar());
    }

    @Override
    public AtencionConsultaDetailResponse obtenerId(Integer idConsulta) {
        return m.AtencionConsultaDetailMapper(r.detalle(idConsulta));
    }

    @Override
    public Integer crear(AtencionConsultaCreateRequest c) {
        AtencionConsulta entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        return r.save(entity).getIdConsulta();
    }

    @Override
    public void actualizar(Integer idConsulta, AtencionConsultaUpdateRequest mt) {
        AtencionConsulta entity = r.getReferenceById(idConsulta);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AtencionConsultaDeleteRequest e) {
        r.eliminar(e.idConsulta(), UsuarioActual.getId());
    }
}
