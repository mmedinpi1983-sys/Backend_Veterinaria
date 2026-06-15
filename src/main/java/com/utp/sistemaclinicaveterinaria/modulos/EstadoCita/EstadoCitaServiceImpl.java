package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.ListItem;
@Service
public class EstadoCitaServiceImpl implements EstadoCitaService {
    private final EstadoCitaRepository repository;
    public EstadoCitaServiceImpl(EstadoCitaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdEstadoCita(), e.getNombre(), e.getDescripcion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EstadoCita e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoCita no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EstadoCita e = new EstadoCita();
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EstadoCita e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoCita no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EstadoCita e = repository.findById(id).orElseThrow(() -> new ApiException("EstadoCita no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(EstadoCita e) { return new Response(e.getIdEstadoCita(), e.getNombre(), e.getDescripcion()); }
}
