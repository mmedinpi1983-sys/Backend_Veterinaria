package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.ListItem;
@Service
public class AtencionEsteticaServiceImpl implements AtencionEsteticaService {
    private final AtencionEsteticaRepository repository;
    public AtencionEsteticaServiceImpl(AtencionEsteticaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdEstetica(), e.getDetalleServicio(), e.getObservaciones(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        AtencionEstetica e = repository.findByIdEsteticaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionEstetica no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        AtencionEstetica e = new AtencionEstetica();
        e.setIdAtencion(request.idAtencion());
        e.setIdServicio(request.idServicio());
        e.setDetalleServicio(request.detalleServicio());
        e.setObservaciones(request.observaciones());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        AtencionEstetica e = repository.findByIdEsteticaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionEstetica no encontrado", "NOT_FOUND"));
        e.setIdAtencion(request.idAtencion());
        e.setIdServicio(request.idServicio());
        e.setDetalleServicio(request.detalleServicio());
        e.setObservaciones(request.observaciones());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        AtencionEstetica e = repository.findByIdEsteticaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionEstetica no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(AtencionEstetica e) { return new Response(e.getIdEstetica(), e.getIdAtencion(), e.getIdServicio(), e.getDetalleServicio(), e.getObservaciones(), e.getIdAsociado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
