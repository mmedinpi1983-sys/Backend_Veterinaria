package com.utp.sistemaclinicaveterinaria.modulos.Programacion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ListItem;
@Service
public class ProgramacionServiceImpl implements ProgramacionService {
    private final ProgramacionRepository repository;
    public ProgramacionServiceImpl(ProgramacionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdProgramacion(), e.getAmbiente(), e.getDescripcion(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Programacion e = repository.findByIdProgramacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Programacion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Programacion e = new Programacion();
        e.setFecha(request.fecha());
        e.setIdTurno(request.idTurno());
        e.setIdEmpleadoRegistrador(request.idEmpleadoRegistrador());
        e.setIdEstadoProgramacion(request.idEstadoProgramacion());
        e.setIdAsociado(request.idAsociado());
        e.setIdCategoria(request.idCategoria());
        e.setIdServicio(request.idServicio());
        e.setAmbiente(request.ambiente());
        e.setDescripcion(request.descripcion());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Programacion e = repository.findByIdProgramacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Programacion no encontrado", "NOT_FOUND"));
        e.setFecha(request.fecha());
        e.setIdTurno(request.idTurno());
        e.setIdEmpleadoRegistrador(request.idEmpleadoRegistrador());
        e.setIdEstadoProgramacion(request.idEstadoProgramacion());
        e.setIdAsociado(request.idAsociado());
        e.setIdCategoria(request.idCategoria());
        e.setIdServicio(request.idServicio());
        e.setAmbiente(request.ambiente());
        e.setDescripcion(request.descripcion());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Programacion e = repository.findByIdProgramacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Programacion no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Programacion e) { return new Response(e.getIdProgramacion(), e.getFecha(), e.getIdTurno(), e.getIdEmpleadoRegistrador(), e.getIdEstadoProgramacion(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdAsociado(), e.getIdCategoria(), e.getIdServicio(), e.getAmbiente(), e.getDescripcion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
