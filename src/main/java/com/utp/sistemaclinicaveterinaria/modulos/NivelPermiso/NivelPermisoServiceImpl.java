package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.ListItem;
@Service
public class NivelPermisoServiceImpl implements NivelPermisoService {
    private final NivelPermisoRepository repository;
    public NivelPermisoServiceImpl(NivelPermisoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdNivelPermiso(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        NivelPermiso e = repository.findByIdNivelPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelPermiso no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        NivelPermiso e = new NivelPermiso();
        e.setIdPermiso(request.idPermiso());
        e.setIdNivelSuscripcion(request.idNivelSuscripcion());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        NivelPermiso e = repository.findByIdNivelPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelPermiso no encontrado", "NOT_FOUND"));
        e.setIdPermiso(request.idPermiso());
        e.setIdNivelSuscripcion(request.idNivelSuscripcion());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        NivelPermiso e = repository.findByIdNivelPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("NivelPermiso no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(NivelPermiso e) { return new Response(e.getIdNivelPermiso(), e.getIdPermiso(), e.getIdNivelSuscripcion(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdSuperAdminCreador(), e.getIdSuperAdminModificador(), e.getIdSuperAdminEliminador()); }
}
