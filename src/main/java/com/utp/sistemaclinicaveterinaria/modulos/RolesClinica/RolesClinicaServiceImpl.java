package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.ListItem;
@Service
public class RolesClinicaServiceImpl implements RolesClinicaService {
    private final RolesClinicaRepository repository;
    public RolesClinicaServiceImpl(RolesClinicaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdRoles(), e.getNombreRol(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        RolesClinica e = repository.findByIdRolesAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolesClinica no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        RolesClinica e = new RolesClinica();
        e.setNombreRol(request.nombreRol());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        RolesClinica e = repository.findByIdRolesAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolesClinica no encontrado", "NOT_FOUND"));
        e.setNombreRol(request.nombreRol());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        RolesClinica e = repository.findByIdRolesAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RolesClinica no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(RolesClinica e) { return new Response(e.getIdRoles(), e.getNombreRol(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdAsociado(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
