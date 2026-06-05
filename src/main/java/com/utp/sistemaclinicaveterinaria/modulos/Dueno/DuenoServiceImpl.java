package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.ListItem;
@Service
public class DuenoServiceImpl implements DuenoService {
    private final DuenoRepository repository;
    public DuenoServiceImpl(DuenoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdDueno(), e.getNombre(), e.getApellidoPaterno(), e.getApellidoMaterno(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Dueno e = repository.findByIdDuenoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Dueno no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Dueno e = new Dueno();
        e.setIdDocumentoIdentidad(request.idDocumentoIdentidad());
        e.setIdAsociado(request.idAsociado());
        e.setNombre(request.nombre());
        e.setApellidoPaterno(request.apellidoPaterno());
        e.setApellidoMaterno(request.apellidoMaterno());
        e.setNroDocumento(request.nroDocumento());
        e.setNroTelefono(request.nroTelefono());
        e.setCorreoElectronico(request.correoElectronico());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Dueno e = repository.findByIdDuenoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Dueno no encontrado", "NOT_FOUND"));
        e.setIdDocumentoIdentidad(request.idDocumentoIdentidad());
        e.setIdAsociado(request.idAsociado());
        e.setNombre(request.nombre());
        e.setApellidoPaterno(request.apellidoPaterno());
        e.setApellidoMaterno(request.apellidoMaterno());
        e.setNroDocumento(request.nroDocumento());
        e.setNroTelefono(request.nroTelefono());
        e.setCorreoElectronico(request.correoElectronico());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Dueno e = repository.findByIdDuenoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Dueno no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Dueno e) { return new Response(e.getIdDueno(), e.getIdDocumentoIdentidad(), e.getIdAsociado(), e.getNombre(), e.getApellidoPaterno(), e.getApellidoMaterno(), e.getNroDocumento(), e.getNroTelefono(), e.getCorreoElectronico(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
