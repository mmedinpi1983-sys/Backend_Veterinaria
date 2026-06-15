package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.ListItem;
@Service
public class AtencionConsultaServiceImpl implements AtencionConsultaService {
    private final AtencionConsultaRepository repository;
    public AtencionConsultaServiceImpl(AtencionConsultaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdConsulta(), e.getEvaluacionClinica(), e.getTratamiento(), e.getIndicaciones(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        AtencionConsulta e = repository.findByIdConsultaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionConsulta no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        AtencionConsulta e = new AtencionConsulta();
        e.setIdAtencion(request.idAtencion());
        e.setEvaluacionClinica(request.evaluacionClinica());
        e.setTratamiento(request.tratamiento());
        e.setIndicaciones(request.indicaciones());
        e.setObservaciones(request.observaciones());
        e.setRequiereControl(request.requiereControl());
        e.setFechaProximoControl(request.fechaProximoControl());
        e.setMotivoConsulta(request.motivoConsulta());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        AtencionConsulta e = repository.findByIdConsultaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionConsulta no encontrado", "NOT_FOUND"));
        e.setIdAtencion(request.idAtencion());
        e.setEvaluacionClinica(request.evaluacionClinica());
        e.setTratamiento(request.tratamiento());
        e.setIndicaciones(request.indicaciones());
        e.setObservaciones(request.observaciones());
        e.setRequiereControl(request.requiereControl());
        e.setFechaProximoControl(request.fechaProximoControl());
        e.setMotivoConsulta(request.motivoConsulta());
        e.setFechaModificacion(LocalDateTime.now());
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        AtencionConsulta e = repository.findByIdConsultaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("AtencionConsulta no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        e.setIdEmpleadoEliminador(UsuarioActual.getId());
        repository.save(e);
    }
    private Response toResponse(AtencionConsulta e) { return new Response(e.getIdConsulta(), e.getIdAtencion(), e.getEvaluacionClinica(), e.getTratamiento(), e.getIndicaciones(), e.getObservaciones(), e.getRequiereControl(), e.getFechaProximoControl(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getMotivoConsulta(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
