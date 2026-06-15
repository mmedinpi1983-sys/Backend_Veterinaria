package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.ListItem;
@Service
public class TriajeServiceImpl implements TriajeService {
    private final TriajeRepository repository;
    public TriajeServiceImpl(TriajeRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdTriaje(), e.getCodigoTemporal(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Triaje e = repository.findByIdTriajeAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Triaje no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Triaje e = new Triaje();
        e.setIdCitaProgramada(request.idCitaProgramada());
        e.setCodigoTemporal(request.codigoTemporal());
        e.setIdMascota(request.idMascota());
        e.setPrioridad(request.prioridad());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setIdMetodoIngreso(request.idMetodoIngreso());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Triaje e = repository.findByIdTriajeAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Triaje no encontrado", "NOT_FOUND"));
        e.setIdCitaProgramada(request.idCitaProgramada());
        e.setCodigoTemporal(request.codigoTemporal());
        e.setIdMascota(request.idMascota());
        e.setPrioridad(request.prioridad());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setIdMetodoIngreso(request.idMetodoIngreso());
        e.setFechaModificacion(LocalDateTime.now());
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Triaje e = repository.findByIdTriajeAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Triaje no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        e.setIdEmpleadoEliminador(UsuarioActual.getId());
        repository.save(e);
    }
    private Response toResponse(Triaje e) { return new Response(e.getIdTriaje(), e.getIdCitaProgramada(), e.getCodigoTemporal(), e.getIdMascota(), e.getPrioridad(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdAsociado(), e.getIdMetodoIngreso(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
