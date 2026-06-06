package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.ListItem;
@Service
public class RolPermisoServiceImpl implements RolPermisoService {
    private final RolPermisoRepository repository;
    public RolPermisoServiceImpl(RolPermisoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdRolPermiso(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        RolPermiso e = repository.findByIdRolPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolPermiso no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        RolPermiso e = new RolPermiso();
        e.setIdRolesClinica(request.idRolesClinica());
        e.setIdPermiso(request.idPermiso());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        RolPermiso e = repository.findByIdRolPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolPermiso no encontrado", "NOT_FOUND"));
        e.setIdRolesClinica(request.idRolesClinica());
        e.setIdPermiso(request.idPermiso());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        RolPermiso e = repository.findByIdRolPermisoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolPermiso no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(RolPermiso e) { return new Response(e.getIdRolPermiso(), e.getIdRolesClinica(), e.getIdPermiso(), e.getIdAsociado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
