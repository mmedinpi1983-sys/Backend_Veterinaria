package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;
import org.springframework.stereotype.Service;

@Service
public class RazaServiceImpl implements RazaService {

    private final RazaRepository r;
    private final RazaMapper m;

    public RazaServiceImpl(RazaRepository r, RazaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<RazaCatalogResponse> catalogo(Integer idAsociado) {
        return m.catalogoMapper(r.catalogo(idAsociado));
    }

    @Override
    public List<RazaListResponse> listar(RazaFilterRequest f) {
        Integer idAsociado = UsuarioActual.getAsociadoId();
        var rows = (f.estado() == null)
                ? r.listarTodos(f.nombre(), idAsociado)
                : r.listar(f.nombre(), f.estado(), idAsociado);
        return m.listarMapper(rows);
    }

    @Override
    public RazaDetailResponse obtenerId(Integer idRaza, Integer idAsociado) {
        return m.RazaDetailMapper(r.detalle(idRaza, idAsociado));
    }

    @Override
    public void crear(RazaCreateRequest c) {
        EspecieRaza entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity = r.save(entity);
    }

    @Override
    public void eliminar(RazaDeleteRequest e) {
        r.eliminar(e.idRaza(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }

    @Override
    public void actualizar(Integer idRaza, RazaUpdateRequest mr) {
        EspecieRaza entity = r.getReferenceById(idRaza);
        m.updateEntity(entity, mr);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

}
