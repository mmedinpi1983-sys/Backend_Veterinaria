package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.ListItem;
@Service
public class TriajeDetalleServiceImpl implements TriajeDetalleService {
    private final TriajeDetalleRepository repository;
    public TriajeDetalleServiceImpl(TriajeDetalleRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdTriajeDetalle(), e.getObservaciones(), e.getAlergias(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        TriajeDetalle e = repository.findByIdTriajeDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("TriajeDetalle no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        TriajeDetalle e = new TriajeDetalle();
        e.setTemperatura(request.temperatura());
        e.setPeso(request.peso());
        e.setObservaciones(request.observaciones());
        e.setAlergias(request.alergias());
        e.setIdTriaje(request.idTriaje());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        TriajeDetalle e = repository.findByIdTriajeDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("TriajeDetalle no encontrado", "NOT_FOUND"));
        e.setTemperatura(request.temperatura());
        e.setPeso(request.peso());
        e.setObservaciones(request.observaciones());
        e.setAlergias(request.alergias());
        e.setIdTriaje(request.idTriaje());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        TriajeDetalle e = repository.findByIdTriajeDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("TriajeDetalle no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(TriajeDetalle e) { return new Response(e.getIdTriajeDetalle(), e.getTemperatura(), e.getPeso(), e.getObservaciones(), e.getAlergias(), e.getIdTriaje(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
