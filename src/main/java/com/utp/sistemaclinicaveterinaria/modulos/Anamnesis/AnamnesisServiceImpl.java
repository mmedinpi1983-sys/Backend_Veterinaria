package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class AnamnesisServiceImpl implements AnamnesisService {
    private final AnamnesisRepository r;
    private final AnamnesisMapper m;

    public AnamnesisServiceImpl(AnamnesisRepository r, AnamnesisMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<AnamnesisListResponse> listar() {
        return m.AnamnesisListMapperList(r.listar());
    }

    @Override
    public AnamnesisDetailResponse obtenerId(Integer idAnamnesis) {
        return m.AnamnesisDetailMapper(r.detalle(idAnamnesis));
    }

    @Override
    public void crear(AnamnesisCreateRequest c) {
        Anamnesis entity = m.toEntity(c);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idAnamnesis, AnamnesisUpdateRequest mt) {
        Anamnesis entity = r.getReferenceById(idAnamnesis);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AnamnesisDeleteRequest e) {
        r.eliminar(e.idAnamnesis(), UsuarioActual.getId());
    }
}
