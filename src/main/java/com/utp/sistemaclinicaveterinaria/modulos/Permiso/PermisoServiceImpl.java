package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.ListItem;
@Service
public class PermisoServiceImpl implements PermisoService {
    private final PermisoRepository repository;
    public PermisoServiceImpl(PermisoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdPermiso(), e.getNombrePermiso(), e.getDescripcionPermiso(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Permiso e = repository.findByIdPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Permiso no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Permiso e = new Permiso();
        e.setNombrePermiso(request.nombrePermiso());
        e.setDescripcionPermiso(request.descripcionPermiso());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Permiso e = repository.findByIdPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Permiso no encontrado", "NOT_FOUND"));
        e.setNombrePermiso(request.nombrePermiso());
        e.setDescripcionPermiso(request.descripcionPermiso());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Permiso e = repository.findByIdPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Permiso no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Permiso e) { return new Response(e.getIdPermiso(), e.getNombrePermiso(), e.getDescripcionPermiso(), e.getIdAsociado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado()); }
}
