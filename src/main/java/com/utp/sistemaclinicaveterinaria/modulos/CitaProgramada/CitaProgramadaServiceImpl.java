package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
@Service
public class CitaProgramadaServiceImpl implements CitaProgramadaService {
    private final CitaProgramadaRepository repository;
    public CitaProgramadaServiceImpl(CitaProgramadaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdCitaProgramada(), e.getMotivo(), e.getMotivoReprogramacion(), e.getFechaCreacion())).toList(); }
    @Override public List<CitaEnriquecida> listarEnriquecido(Integer idEstado, Integer idEmpleado, String fechaInicio, String fechaFin, String busqueda) {
        return repository.findCitasFiltradas(idEstado, idEmpleado, fechaInicio, fechaFin, busqueda)
            .stream().map(v -> new CitaEnriquecida(
                v.getIdCitaProgramada(), v.getFecha(), v.getHoraInicio(), v.getHoraFin(),
                v.getNombreMascota(), v.getEspecie(), v.getRaza(), v.getNombreDueno(),
                v.getNombreServicio(), v.getNombreVeterinario(), v.getEstadoCita(),
                v.getIdEstadoCita(), v.getMotivo()
            )).toList();
    }
    @Override public StatsResponse getStats() {
        Object[] row = repository.getStatsMes();
        if (row == null || row.length == 0) return new StatsResponse(0, 0, 0, 0);
        Object[] r = (Object[]) row[0];
        return new StatsResponse(
            r[0] != null ? ((Number) r[0]).longValue() : 0,
            r[1] != null ? ((Number) r[1]).longValue() : 0,
            r[2] != null ? ((Number) r[2]).longValue() : 0,
            r[3] != null ? ((Number) r[3]).longValue() : 0
        );
    }
    @Override public Response obtenerPorId(Integer id) {
        CitaProgramada e = repository.findByIdCitaProgramadaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("CitaProgramada no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        CitaProgramada e = new CitaProgramada();
        e.setIdDueno(request.idDueno());
        e.setIdProgramacion(request.idProgramacion());
        e.setIdMascota(request.idMascota());
        e.setFecha(request.fecha());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setIdEstadoCita(request.idEstadoCita());
        e.setMotivo(request.motivo());
        e.setIdAsociado(request.idAsociado());
        e.setIdCategoria(request.idCategoria());
        e.setIdServicio(request.idServicio());
        e.setMotivoReprogramacion(request.motivoReprogramacion());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        CitaProgramada e = repository.findByIdCitaProgramadaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("CitaProgramada no encontrado", "NOT_FOUND"));
        e.setIdDueno(request.idDueno());
        e.setIdProgramacion(request.idProgramacion());
        e.setIdMascota(request.idMascota());
        e.setFecha(request.fecha());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setIdEstadoCita(request.idEstadoCita());
        e.setMotivo(request.motivo());
        e.setIdAsociado(request.idAsociado());
        e.setIdCategoria(request.idCategoria());
        e.setIdServicio(request.idServicio());
        e.setMotivoReprogramacion(request.motivoReprogramacion());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        CitaProgramada e = repository.findByIdCitaProgramadaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("CitaProgramada no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(CitaProgramada e) { return new Response(e.getIdCitaProgramada(), e.getIdDueno(), e.getIdProgramacion(), e.getIdMascota(), e.getFecha(), e.getHoraInicio(), e.getHoraFin(), e.getIdEstadoCita(), e.getMotivo(), e.getIdAsociado(), e.getIdCategoria(), e.getIdServicio(), e.getMotivoReprogramacion(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
