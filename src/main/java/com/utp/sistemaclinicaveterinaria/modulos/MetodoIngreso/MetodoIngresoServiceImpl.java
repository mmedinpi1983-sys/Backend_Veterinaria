package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.ListItem;
@Service
public class MetodoIngresoServiceImpl implements MetodoIngresoService {
    private final MetodoIngresoRepository repository;
    public MetodoIngresoServiceImpl(MetodoIngresoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdMetodoIngreso(), e.getNombre(), e.getDescripcion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        MetodoIngreso e = repository.findById(id).orElseThrow(() -> new ApiException("MetodoIngreso no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        MetodoIngreso e = new MetodoIngreso();
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        MetodoIngreso e = repository.findById(id).orElseThrow(() -> new ApiException("MetodoIngreso no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        MetodoIngreso e = repository.findById(id).orElseThrow(() -> new ApiException("MetodoIngreso no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(MetodoIngreso e) { return new Response(e.getIdMetodoIngreso(), e.getNombre(), e.getDescripcion(), e.getIdAsociado(), e.getEstado()); }
}
