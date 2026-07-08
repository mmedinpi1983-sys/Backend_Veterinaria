package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoUpdateRequest;

@Service
public class MedicamentoCatalogoServiceImpl implements MedicamentoCatalogoService {
    private final MedicamentoCatalogoRepository r;
    private final MedicamentoCatalogoMapper m;

    public MedicamentoCatalogoServiceImpl(MedicamentoCatalogoRepository r, MedicamentoCatalogoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<MedicamentoCatalogoCatalogResponse> catalogo() {
        return m.MedicamentoCatalogoCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<MedicamentoCatalogoListResponse> listar() {
        return m.MedicamentoCatalogoListMapperList(r.listar());
    }

    @Override
    public MedicamentoCatalogoDetailResponse obtenerId(Integer id) {
        return m.MedicamentoCatalogoDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(MedicamentoCatalogoCreateRequest c) {
        MedicamentoCatalogo entity = m.toEntity(c);
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, MedicamentoCatalogoUpdateRequest mt) {
        MedicamentoCatalogo entity = r.getReferenceById(id);
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(MedicamentoCatalogoDeleteRequest e) {
        r.deleteById(e.idMedicamento());
    }
}
