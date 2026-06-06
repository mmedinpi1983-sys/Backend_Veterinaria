package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.ListItem;
@Service
public class PresentacionServiceImpl implements PresentacionService {
    private final PresentacionRepository repository;
    public PresentacionServiceImpl(PresentacionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdPresentacion(), e.getNombre(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Presentacion e = repository.findByIdPresentacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Presentacion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Presentacion e = new Presentacion();
        e.setNombre(request.nombre());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Presentacion e = repository.findByIdPresentacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Presentacion no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Presentacion e = repository.findByIdPresentacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Presentacion no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Presentacion e) { return new Response(e.getIdPresentacion(), e.getNombre(), e.getIdAsociado(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
