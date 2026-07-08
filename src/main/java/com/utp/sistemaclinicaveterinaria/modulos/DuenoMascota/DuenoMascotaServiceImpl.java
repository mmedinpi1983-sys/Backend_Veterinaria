package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class DuenoMascotaServiceImpl implements DuenoMascotaService {
    private final DuenoMascotaRepository r;
    private final DuenoMascotaMapper m;

    public DuenoMascotaServiceImpl(DuenoMascotaRepository r, DuenoMascotaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<DuenoMascotaCatalogResponse> catalogo(Integer idAsociado) {
        return m.DuenoMascotaCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<DuenoMascotaListResponse> listar(Integer idAsociado) {
        return m.DuenoMascotaListMapperList(r.listar(idAsociado));
    }

    @Override
    public DuenoMascotaDetailResponse obtenerId(Integer idDuenoMascota, Integer idAsociado) {
        return m.DuenoMascotaDetailMapper(r.detalle(idDuenoMascota, idAsociado));
    }

    @Override
    public void crear(DuenoMascotaCreateRequest c) {
        DuenoMascota entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idDuenoMascota, DuenoMascotaUpdateRequest u) {
        DuenoMascota entity = r.getReferenceById(idDuenoMascota);
        m.updateEntity(entity, u);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(DuenoMascotaDeleteRequest e) {
        r.eliminar(e.idDuenoMascota(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
