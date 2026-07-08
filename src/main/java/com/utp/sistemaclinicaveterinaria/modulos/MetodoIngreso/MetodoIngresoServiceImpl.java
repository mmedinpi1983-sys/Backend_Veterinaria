package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class MetodoIngresoServiceImpl implements MetodoIngresoService {
    private final MetodoIngresoRepository r;
    private final MetodoIngresoMapper m;

    public MetodoIngresoServiceImpl(MetodoIngresoRepository r, MetodoIngresoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<MetodoIngresoCatalogResponse> catalogo(Integer idAsociado) {
        return m.MetodoIngresoCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<MetodoIngresoListResponse> listar(Integer idAsociado) {
        return m.MetodoIngresoListMapperList(r.listar(idAsociado));
    }

    @Override
    public MetodoIngresoDetailResponse obtenerId(Integer idMetodoIngreso, Integer idAsociado) {
        return m.MetodoIngresoDetailMapper(r.detalle(idMetodoIngreso, idAsociado));
    }

    @Override
    public void crear(MetodoIngresoCreateRequest c) {
        MetodoIngreso entity = m.toEntity(c);
        entity.setEstado(true);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idMetodoIngreso, MetodoIngresoUpdateRequest mt) {
        MetodoIngreso entity = r.getReferenceById(idMetodoIngreso);
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(MetodoIngresoDeleteRequest e) {
        r.eliminar(e.idMetodoIngreso(), UsuarioActual.getAsociadoId());
    }
}
