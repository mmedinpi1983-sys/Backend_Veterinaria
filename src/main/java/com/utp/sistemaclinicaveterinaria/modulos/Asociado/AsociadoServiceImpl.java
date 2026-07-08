package com.utp.sistemaclinicaveterinaria.modulos.Asociado;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoUpdateRequest;

@Service
public class AsociadoServiceImpl implements AsociadoService {
    private final AsociadoRepository r;
    private final AsociadoMapper m;

    public AsociadoServiceImpl(AsociadoRepository r, AsociadoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<AsociadoListResponse> listar() {
        return m.AsociadoListMapperList(r.listar());
    }

    @Override
    public AsociadoDetailResponse obtenerId(Integer idAsociado) {
        return m.AsociadoDetailMapper(r.detalle(idAsociado));
    }

    @Override
    public void crear(AsociadoCreateRequest c) {
        Asociado entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idAsociado, AsociadoUpdateRequest mt) {
        Asociado entity = r.getReferenceById(idAsociado);
        m.updateEntity(entity, mt);
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AsociadoDeleteRequest e) {
        r.eliminar(e.idAsociado());
    }
}
