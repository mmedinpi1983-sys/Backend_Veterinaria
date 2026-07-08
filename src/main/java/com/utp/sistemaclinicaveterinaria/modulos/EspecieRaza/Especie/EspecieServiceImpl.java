package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import org.springframework.stereotype.Service;

@Service
public class EspecieServiceImpl implements EspecieService {
    private final EspecieRepository r;
    private final EspecieMapper m;

    public EspecieServiceImpl(EspecieRepository r, EspecieMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<EspecieCatalogResponse> catalogo() {
        return m.catalogoMapper(r.catalogo(UsuarioActual.getAsociadoId()));
    }

    @Override
    public List<EspecieListResponse> listar(EspecieFilterRequest f) {
        return m.listarMapper(r.listar(f.nombre(), f.estado(), UsuarioActual.getAsociadoId()));
    }

    @Override
    public EspecieDetailResponse obtenerPorId(Integer idEspecie, Integer idAsociado) {
        return m.EspecieDetailMapper(r.detalle(idEspecie, idAsociado));
    }

    @Override
    public void crear(EspecieCreateRequest c) {
        EspecieRaza entity = m.toEntity(c);
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity = r.save(entity);
    }

    @Override
    public void actualizar(Integer idEspecie, EspecieUpdateRequest me) {
        EspecieRaza entity = r.getReferenceById(idEspecie);
        m.updateEntity(entity, me);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(EspecieDeleteRequest e) {
        r.eliminar(e.idEspecie(), e.idUsuario(), e.idAsociado());
    }

}