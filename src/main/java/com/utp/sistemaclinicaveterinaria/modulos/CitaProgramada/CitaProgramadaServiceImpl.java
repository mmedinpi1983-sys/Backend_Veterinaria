package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.VeterinarioDisponible;
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
    // Valida que la hora de fin sea posterior a la de inicio (no se puede expresar con anotaciones de campo)
    private void validarHoras(Request request) {
        if (request.horaFin() != null && request.horaInicio() != null
                && !request.horaFin().isAfter(request.horaInicio())) {
            throw new ApiException("La hora de fin debe ser posterior a la hora de inicio", "VALIDATION_ERROR");
        }
    }
    // Valida que el horario no esté ya ocupado por otra cita (no cancelada) del mismo veterinario ese día.
    // excluirId ignora la propia cita al reprogramar (su horario actual no debe contar como ocupado).
    private void validarDisponibilidad(Request request, Integer excluirId) {
        if (request.idProgramacion() == null || request.fecha() == null || request.horaInicio() == null) return;
        String hora = request.horaInicio().toString();
        if (hora.length() >= 5) hora = hora.substring(0, 5);
        if (repository.findHorasOcupadas(request.idProgramacion(), request.fecha().toString(), excluirId).contains(hora)) {
            throw new ApiException("Ese horario ya está ocupado para el veterinario seleccionado", "VALIDATION_ERROR");
        }
    }
    @Override public Response crear(Request request) {
        validarHoras(request);
        validarDisponibilidad(request, null);
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
        e.setIdEmpleadoCreador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        validarHoras(request);
        validarDisponibilidad(request, id);
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
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        CitaProgramada e = repository.findByIdCitaProgramadaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("CitaProgramada no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        e.setIdEmpleadoEliminador(UsuarioActual.getId());
        repository.save(e);
    }
    @Override public List<CitaEnriquecida> listarPorMascota(Integer idMascota) {
        return repository.findCitasByMascota(idMascota).stream().map(v -> new CitaEnriquecida(
            v.getIdCitaProgramada(), v.getFecha(), v.getHoraInicio(), v.getHoraFin(),
            v.getNombreMascota(), v.getEspecie(), v.getRaza(), v.getNombreDueno(),
            v.getNombreServicio(), v.getNombreVeterinario(), v.getEstadoCita(),
            v.getIdEstadoCita(), v.getMotivo())).toList();
    }
    @Override public List<VeterinarioDisponible> listarVeterinarios() {
        return repository.findVeterinariosDisponibles().stream().map(v -> new VeterinarioDisponible(
            v.getIdProgramacion(), v.getNombreVeterinario(), v.getNombreTurno(), v.getHoraInicio(), v.getHoraFin()
        )).toList();
    }
    @Override public List<String> horasOcupadas(Integer idProgramacion, String fecha, Integer excluirId) {
        return repository.findHorasOcupadas(idProgramacion, fecha, excluirId);
    }
    private Response toResponse(CitaProgramada e) { return new Response(e.getIdCitaProgramada(), e.getIdDueno(), e.getIdProgramacion(), e.getIdMascota(), e.getFecha(), e.getHoraInicio(), e.getHoraFin(), e.getIdEstadoCita(), e.getMotivo(), e.getIdAsociado(), e.getIdCategoria(), e.getIdServicio(), e.getMotivoReprogramacion(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}