package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadUpdateRequest;

public interface DocumentoIdentidadService {
    List<DocumentoIdentidadCatalogResponse> catalogo(Integer idAsociado);
    List<DocumentoIdentidadListResponse> listar(Integer idAsociado);
    DocumentoIdentidadDetailResponse obtenerId(Integer idDocumentoIdentidad);
    void crear(DocumentoIdentidadCreateRequest c);
    void actualizar(Integer idDocumentoIdentidad, DocumentoIdentidadUpdateRequest mt);
    void eliminar(DocumentoIdentidadDeleteRequest e);
}
