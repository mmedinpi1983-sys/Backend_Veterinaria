package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {
    private final ConsultorioRepository r;
    private final ConsultorioMapper m;

    public ConsultorioServiceImpl(ConsultorioRepository r, ConsultorioMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<ConsultorioCatalogResponse> catalogo(Integer idAsociado) {
        return m.toCatalogResponseList(r.catalogo(idAsociado));
    }

    @Override
    public List<ConsultorioListResponse> listar(ConsultorioFilterRequest f) {
        Boolean estado = f == null || f.estado() == null ? Boolean.TRUE : f.estado();
        String nombre = f == null || f.nombre() == null ? "" : f.nombre().trim();
        return m.toListResponseList(r.listar(UsuarioActual.getAsociadoId(), estado, nombre));
    }

    @Override
    public ConsultorioDetailResponse obtenerId(Integer idConsultorio, Integer idAsociado) {
        return m.toDetailResponse(r.detalle(idConsultorio, idAsociado));
    }

    @Override
    public void crear(ConsultorioCreateRequest c) {
        Consultorio entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idConsultorio, ConsultorioUpdateRequest mt) {
        Consultorio entity = r.getReferenceById(idConsultorio);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(ConsultorioDeleteRequest e) {
        r.eliminar(e.idConsultorio(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
