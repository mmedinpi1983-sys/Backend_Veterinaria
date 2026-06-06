package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.ListItem;
@Service
public class NivelSuscripcionServiceImpl implements NivelSuscripcionService {
    private final NivelSuscripcionRepository repository;
    public NivelSuscripcionServiceImpl(NivelSuscripcionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdNivel(), e.getNombre(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        NivelSuscripcion e = repository.findByIdNivelAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelSuscripcion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        NivelSuscripcion e = new NivelSuscripcion();
        e.setNombre(request.nombre());
        e.setCantidadUsuario(request.cantidadUsuario());
        e.setPrecioMensual(request.precioMensual());
        e.setPrecioAnual(request.precioAnual());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        NivelSuscripcion e = repository.findByIdNivelAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelSuscripcion no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setCantidadUsuario(request.cantidadUsuario());
        e.setPrecioMensual(request.precioMensual());
        e.setPrecioAnual(request.precioAnual());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        NivelSuscripcion e = repository.findByIdNivelAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelSuscripcion no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(NivelSuscripcion e) { return new Response(e.getIdNivel(), e.getNombre(), e.getCantidadUsuario(), e.getPrecioMensual(), e.getPrecioAnual(), e.getEstado(), e.getFechaCreacion(), e.getFechaEliminacion(), e.getFechaModificacion(), e.getIdSuperAdminCreador(), e.getIdSuperAdminModificador(), e.getIdSuperAdminEliminador()); }
}
