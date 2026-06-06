package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.ListItem;
@Service
public class DocumentoIdentidadServiceImpl implements DocumentoIdentidadService {
    private final DocumentoIdentidadRepository repository;
    public DocumentoIdentidadServiceImpl(DocumentoIdentidadRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdDocumentoIdentidad(), e.getDescripcion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        DocumentoIdentidad e = repository.findById(id).orElseThrow(() -> new ApiException("DocumentoIdentidad no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        DocumentoIdentidad e = new DocumentoIdentidad();
        e.setDescripcion(request.descripcion());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        DocumentoIdentidad e = repository.findById(id).orElseThrow(() -> new ApiException("DocumentoIdentidad no encontrado", "NOT_FOUND"));
        e.setDescripcion(request.descripcion());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        DocumentoIdentidad e = repository.findById(id).orElseThrow(() -> new ApiException("DocumentoIdentidad no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(DocumentoIdentidad e) { return new Response(e.getIdDocumentoIdentidad(), e.getDescripcion(), e.getEstado(), e.getIdAsociado()); }
}
