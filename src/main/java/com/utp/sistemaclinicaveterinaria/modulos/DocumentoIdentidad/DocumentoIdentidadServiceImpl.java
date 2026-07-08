package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;

import org.springframework.stereotype.Service;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class DocumentoIdentidadServiceImpl implements DocumentoIdentidadService {
    private final DocumentoIdentidadRepository r;
    private final DocumentoIdentidadMapper m;

    public DocumentoIdentidadServiceImpl(DocumentoIdentidadRepository r, DocumentoIdentidadMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<DocumentoIdentidadCatalogResponse> catalogo(Integer idAsociado) {
        return m.DocumentoIdentidadCatalogoMapperList(r.catalogo(idAsociado));
    }

    @Override
    public List<DocumentoIdentidadListResponse> listar(Integer idAsociado) {
        return m.DocumentoIdentidadListMapperList(r.listar(idAsociado));
    }

    @Override
    public DocumentoIdentidadDetailResponse obtenerId(Integer idDocumentoIdentidad) {
        return m.DocumentoIdentidadDetailMapper(r.detalle(idDocumentoIdentidad));
    }

    @Override
    public void crear(DocumentoIdentidadCreateRequest c) {
        DocumentoIdentidad entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idDocumentoIdentidad, DocumentoIdentidadUpdateRequest mt) {
        DocumentoIdentidad entity = r.getReferenceById(idDocumentoIdentidad);
        m.updateEntity(entity, mt);
        r.save(entity);
    }

    @Override
    public void eliminar(DocumentoIdentidadDeleteRequest e) {
        r.eliminar(e.idDocumentoIdentidad());
    }
}
