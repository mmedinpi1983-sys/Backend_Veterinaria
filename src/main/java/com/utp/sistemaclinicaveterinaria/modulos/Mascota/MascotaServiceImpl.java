package com.utp.sistemaclinicaveterinaria.modulos.Mascota;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class MascotaServiceImpl implements MascotaService {
    private final MascotaRepository r;
    private final MascotaMapper m;

    public MascotaServiceImpl(MascotaRepository r, MascotaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<MascotaCatalogResponse> catalogo(Integer idAsociado) {
        return m.MascotaCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<MascotaListResponse> listar(MascotaFilterRequest f) {
        return m.MascotaListMapperList(r.listar(UsuarioActual.getAsociadoId(), f.estado(), f.q()));
    }

    @Override
    public MascotaDetailResponse obtenerId(Integer idMascota, Integer idAsociado) {
        return m.MascotaDetailMapper(r.detalle(idMascota, idAsociado));
    }

    @Override
    public void crear(MascotaCreateRequest c) {
        Mascota entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idMascota, MascotaUpdateRequest u) {
        Mascota entity = r.getReferenceById(idMascota);
        m.updateEntity(entity, u);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(MascotaDeleteRequest e) {
        r.eliminar(e.idMascota(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }

    @Override
    public List<SearchItem> buscar(String q) {
        return r.buscarConDueno(q == null || q.isBlank() ? null : q).stream()
            .map(v -> new SearchItem(v.getIdMascota(), v.getNombre(), v.getEspecie(), v.getRaza(),
                    v.getIdDueno(), v.getNombreDueno(), v.getTamanio(), v.getSexo()))
            .toList();
    }
}
