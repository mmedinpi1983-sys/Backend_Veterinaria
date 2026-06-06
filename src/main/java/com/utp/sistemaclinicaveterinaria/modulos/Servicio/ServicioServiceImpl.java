package com.utp.sistemaclinicaveterinaria.modulos.Servicio;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ListItem;
@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository repository;
    public ServicioServiceImpl(ServicioRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdServicio(), e.getNombre(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Servicio e = repository.findByIdServicioAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Servicio no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Servicio e = new Servicio();
        e.setNombre(request.nombre());
        e.setIdCategoria(request.idCategoria());
        e.setIdAsociado(request.idAsociado());
        e.setPrecio(request.precio());
        e.setRequiereTriaje(request.requiereTriaje());
        e.setEstado(request.estado());
        e.setDuracionEstimada(request.duracionEstimada());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Servicio e = repository.findByIdServicioAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Servicio no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setIdCategoria(request.idCategoria());
        e.setIdAsociado(request.idAsociado());
        e.setPrecio(request.precio());
        e.setRequiereTriaje(request.requiereTriaje());
        e.setEstado(request.estado());
        e.setDuracionEstimada(request.duracionEstimada());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Servicio e = repository.findByIdServicioAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Servicio no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Servicio e) { return new Response(e.getIdServicio(), e.getNombre(), e.getIdCategoria(), e.getIdAsociado(), e.getPrecio(), e.getRequiereTriaje(), e.getEstado(), e.getDuracionEstimada(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
