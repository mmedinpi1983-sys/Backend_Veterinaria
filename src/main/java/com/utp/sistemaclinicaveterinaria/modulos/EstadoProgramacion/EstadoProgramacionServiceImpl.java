package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.ListItem;
@Service
public class EstadoProgramacionServiceImpl implements EstadoProgramacionService {
    private final EstadoProgramacionRepository repository;
    public EstadoProgramacionServiceImpl(EstadoProgramacionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdEstadoProgramacion(), e.getNombre(), e.getDescripcion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EstadoProgramacion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoProgramacion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EstadoProgramacion e = new EstadoProgramacion();
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EstadoProgramacion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoProgramacion no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EstadoProgramacion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoProgramacion no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(EstadoProgramacion e) { return new Response(e.getIdEstadoProgramacion(), e.getNombre(), e.getDescripcion(), e.getIdAsociado(), e.getEstado()); }
}
