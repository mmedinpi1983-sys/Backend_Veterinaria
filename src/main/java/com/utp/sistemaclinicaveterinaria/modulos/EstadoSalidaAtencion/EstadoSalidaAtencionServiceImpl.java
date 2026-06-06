package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.ListItem;
@Service
public class EstadoSalidaAtencionServiceImpl implements EstadoSalidaAtencionService {
    private final EstadoSalidaAtencionRepository repository;
    public EstadoSalidaAtencionServiceImpl(EstadoSalidaAtencionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdEstadoSalida(), e.getNombre(), e.getDescripcion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EstadoSalidaAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoSalidaAtencion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EstadoSalidaAtencion e = new EstadoSalidaAtencion();
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EstadoSalidaAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoSalidaAtencion no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EstadoSalidaAtencion e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoSalidaAtencion no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(EstadoSalidaAtencion e) { return new Response(e.getIdEstadoSalida(), e.getNombre(), e.getDescripcion()); }
}
