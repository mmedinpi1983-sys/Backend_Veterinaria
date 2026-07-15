package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoUpdateRequest;

@Service
public class DiagnosticoCatalogoServiceImpl implements DiagnosticoCatalogoService {
    private final DiagnosticoCatalogoRepository r;
    private final DiagnosticoCatalogoMapper m;

    public DiagnosticoCatalogoServiceImpl(DiagnosticoCatalogoRepository r, DiagnosticoCatalogoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<DiagnosticoCatalogoCatalogResponse> catalogo() {
        return m.DiagnosticoCatalogoCatalogoMapperList(r.catalogo());
    }

    @Override
    public List<DiagnosticoCatalogoListResponse> listar() {
        return m.DiagnosticoCatalogoListMapperList(r.listar());
    }

    @Override
    public DiagnosticoCatalogoDetailResponse obtenerId(Integer id) {
        return m.DiagnosticoCatalogoDetailMapper(r.detalle(id));
    }

    @Override
    public void crear(DiagnosticoCatalogoCreateRequest c) {
        DiagnosticoCatalogo entity = m.toEntity(c);
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, DiagnosticoCatalogoUpdateRequest mt) {
        DiagnosticoCatalogo entity = r.getReferenceById(id);
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(DiagnosticoCatalogoDeleteRequest e) {
        r.deleteById(e.idDiagnosticoCatalogo());
    }
}
