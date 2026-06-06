package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.ListItem;
@Service
public class EstadoAtencionServiceImpl implements EstadoAtencionService {
    private final EstadoAtencionRepository repository;
    public EstadoAtencionServiceImpl(EstadoAtencionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdEstadoAtencion(), e.getNombre())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EstadoAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoAtencion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EstadoAtencion e = new EstadoAtencion();
        e.setNombre(request.nombre());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EstadoAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoAtencion no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EstadoAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoAtencion no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(EstadoAtencion e) { return new Response(e.getIdEstadoAtencion(), e.getNombre()); }
}
