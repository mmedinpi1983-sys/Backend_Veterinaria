package com.utp.sistemaclinicaveterinaria.modulos.Turno;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.ListItem;
@Service
public class TurnoServiceImpl implements TurnoService {
    private final TurnoRepository repository;
    public TurnoServiceImpl(TurnoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdTurno(), e.getNombre(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Turno e = repository.findByIdTurnoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Turno no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Turno e = new Turno();
        e.setNombre(request.nombre());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Turno e = repository.findByIdTurnoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Turno no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Turno e = repository.findByIdTurnoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Turno no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Turno e) { return new Response(e.getIdTurno(), e.getNombre(), e.getHoraInicio(), e.getHoraFin(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdAsociado(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
