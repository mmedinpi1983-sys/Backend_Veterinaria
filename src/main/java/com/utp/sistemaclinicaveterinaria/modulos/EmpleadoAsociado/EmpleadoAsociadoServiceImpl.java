package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.ListItem;
@Service
public class EmpleadoAsociadoServiceImpl implements EmpleadoAsociadoService {
    private final EmpleadoAsociadoRepository repository;
    public EmpleadoAsociadoServiceImpl(EmpleadoAsociadoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdEmpleadoAsociado(), e.getCorreo(), e.getContrasena(), e.getNombreEmpleado(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EmpleadoAsociado e = repository.findByIdEmpleadoAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EmpleadoAsociado no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EmpleadoAsociado e = new EmpleadoAsociado();
        e.setIdAsociado(request.idAsociado());
        e.setCorreo(request.correo());
        e.setContrasena(request.contrasena());
        e.setNombreEmpleado(request.nombreEmpleado());
        e.setApellidoPaterno(request.apellidoPaterno());
        e.setApellidoMaterno(request.apellidoMaterno());
        e.setNroDocumento(request.nroDocumento());
        e.setFechaNacimiento(request.fechaNacimiento());
        e.setIdDocumentoIdentidad(request.idDocumentoIdentidad());
        e.setIdRolesClinica(request.idRolesClinica());
        e.setCorreoElectronico(request.correoElectronico());
        e.setNroTelefono(request.nroTelefono());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EmpleadoAsociado e = repository.findByIdEmpleadoAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EmpleadoAsociado no encontrado", "NOT_FOUND"));
        e.setIdAsociado(request.idAsociado());
        e.setCorreo(request.correo());
        e.setContrasena(request.contrasena());
        e.setNombreEmpleado(request.nombreEmpleado());
        e.setApellidoPaterno(request.apellidoPaterno());
        e.setApellidoMaterno(request.apellidoMaterno());
        e.setNroDocumento(request.nroDocumento());
        e.setFechaNacimiento(request.fechaNacimiento());
        e.setIdDocumentoIdentidad(request.idDocumentoIdentidad());
        e.setIdRolesClinica(request.idRolesClinica());
        e.setCorreoElectronico(request.correoElectronico());
        e.setNroTelefono(request.nroTelefono());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EmpleadoAsociado e = repository.findByIdEmpleadoAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EmpleadoAsociado no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(EmpleadoAsociado e) { return new Response(e.getIdEmpleadoAsociado(), e.getIdAsociado(), e.getCorreo(), e.getContrasena(), e.getNombreEmpleado(), e.getApellidoPaterno(), e.getApellidoMaterno(), e.getNroDocumento(), e.getFechaNacimiento(), e.getIdDocumentoIdentidad(), e.getIdRolesClinica(), e.getCorreoElectronico(), e.getNroTelefono(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
