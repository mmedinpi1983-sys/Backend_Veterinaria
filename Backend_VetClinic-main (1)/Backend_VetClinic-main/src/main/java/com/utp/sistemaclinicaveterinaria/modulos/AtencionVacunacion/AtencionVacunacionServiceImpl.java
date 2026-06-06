package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.ListItem;
@Service
public class AtencionVacunacionServiceImpl implements AtencionVacunacionService {
    private final AtencionVacunacionRepository repository;
    public AtencionVacunacionServiceImpl(AtencionVacunacionRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdVacunacion(), e.getObservaciones(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        AtencionVacunacion e = repository.findByIdVacunacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionVacunacion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        AtencionVacunacion e = new AtencionVacunacion();
        e.setIdAtencion(request.idAtencion());
        e.setIdVacuna(request.idVacuna());
        e.setDosis(request.dosis());
        e.setFechaAplicacion(request.fechaAplicacion());
        e.setFechaRefuerzo(request.fechaRefuerzo());
        e.setObservaciones(request.observaciones());
        e.setEstado(request.estado());
        e.setIdConsulta(request.idConsulta());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        AtencionVacunacion e = repository.findByIdVacunacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionVacunacion no encontrado", "NOT_FOUND"));
        e.setIdAtencion(request.idAtencion());
        e.setIdVacuna(request.idVacuna());
        e.setDosis(request.dosis());
        e.setFechaAplicacion(request.fechaAplicacion());
        e.setFechaRefuerzo(request.fechaRefuerzo());
        e.setObservaciones(request.observaciones());
        e.setEstado(request.estado());
        e.setIdConsulta(request.idConsulta());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        AtencionVacunacion e = repository.findByIdVacunacionAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionVacunacion no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(AtencionVacunacion e) { return new Response(e.getIdVacunacion(), e.getIdAtencion(), e.getIdVacuna(), e.getDosis(), e.getFechaAplicacion(), e.getFechaRefuerzo(), e.getObservaciones(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getEstado(), e.getIdConsulta(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
