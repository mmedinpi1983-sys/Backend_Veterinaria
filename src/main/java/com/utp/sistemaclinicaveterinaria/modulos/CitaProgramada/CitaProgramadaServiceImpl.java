package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.VeterinarioDisponible;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ProgramacionCita;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class CitaProgramadaServiceImpl implements CitaProgramadaService {

    private final CitaProgramadaRepository r;
    private final CitaProgramadaMapper m;

    public CitaProgramadaServiceImpl(CitaProgramadaRepository r, CitaProgramadaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<CitaProgramadaListResponse> listar() {
        return m.CitaProgramadaListMapperList(r.listar());
    }

    @Override
    public CitaProgramadaDetailResponse obtenerId(Integer idCitaProgramada) {
        return m.CitaProgramadaDetailMapper(r.detalle(idCitaProgramada));
    }

    @Override
    public List<CitaEnriquecida> listarEnriquecido(Integer idEstado, Integer idEmpleado, String fechaInicio, String fechaFin, String busqueda) {
        return r.findCitasFiltradas(idEstado, idEmpleado, fechaInicio, fechaFin, busqueda)
            .stream().map(v -> new CitaEnriquecida(
                v.getIdCitaProgramada(), v.getFecha(), v.getHoraInicio(), v.getHoraFin(),
                v.getNombreMascota(), v.getEspecie(), v.getRaza(), v.getNombreDueno(),
                v.getNombreServicio(), v.getNombreVeterinario(), v.getEstadoCita(),
                v.getIdEstadoCita(), v.getMotivo(), v.getIdAtencion()
            )).toList();
    }

    @Override
    public StatsResponse getStats() {
        Object[] row = r.getStatsMes();
        if (row == null || row.length == 0) return new StatsResponse(0, 0, 0, 0);
        Object[] r2 = (Object[]) row[0];
        return new StatsResponse(
            r2[0] != null ? ((Number) r2[0]).longValue() : 0,
            r2[1] != null ? ((Number) r2[1]).longValue() : 0,
            r2[2] != null ? ((Number) r2[2]).longValue() : 0,
            r2[3] != null ? ((Number) r2[3]).longValue() : 0
        );
    }

    private void validarHoras(CitaProgramadaCreateRequest request) {
        if (request.horaFin() != null && request.horaInicio() != null
                && !request.horaFin().isAfter(request.horaInicio())) {
            throw new ApiException("La hora de fin debe ser posterior a la hora de inicio", "VALIDATION_ERROR");
        }
    }

    private void validarDisponibilidad(CitaProgramadaCreateRequest request, Integer excluirId) {
        if (request.idProgramacion() == null || request.fecha() == null || request.horaInicio() == null) return;
        String hora = request.horaInicio().toString();
        if (hora.length() >= 5) hora = hora.substring(0, 5);
        if (r.findHorasOcupadas(request.idProgramacion(), request.fecha().toString(), excluirId).contains(hora)) {
            throw new ApiException("Ese horario ya está ocupado para el veterinario seleccionado", "VALIDATION_ERROR");
        }
    }

    @Override
    public void crear(CitaProgramadaCreateRequest c) {
        validarHoras(c);
        validarDisponibilidad(c, null);
        CitaProgramada entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idCitaProgramada, CitaProgramadaUpdateRequest mt) {
        CitaProgramada entity = r.getReferenceById(idCitaProgramada);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(CitaProgramadaDeleteRequest e) {
        r.eliminar(e.idCitaProgramada(), UsuarioActual.getId());
    }

    @Override
    public List<CitaEnriquecida> listarPorMascota(Integer idMascota) {
        return r.findCitasByMascota(idMascota).stream().map(v -> new CitaEnriquecida(
            v.getIdCitaProgramada(), v.getFecha(), v.getHoraInicio(), v.getHoraFin(),
            v.getNombreMascota(), v.getEspecie(), v.getRaza(), v.getNombreDueno(),
            v.getNombreServicio(), v.getNombreVeterinario(), v.getEstadoCita(),
            v.getIdEstadoCita(), v.getMotivo(), v.getIdAtencion())).toList();
    }

    @Override
    public List<VeterinarioDisponible> listarVeterinarios() {
        return r.findVeterinariosDisponibles().stream().map(v -> new VeterinarioDisponible(
            v.getIdProgramacion(), v.getNombreVeterinario(), v.getNombreTurno(), v.getHoraInicio(), v.getHoraFin()
        )).toList();
    }

    @Override
    public List<ProgramacionCita> listarProgramacionesCita() {
        return r.findProgramacionesParaCita(UsuarioActual.getAsociadoId()).stream().map(v -> new ProgramacionCita(
            v.getIdProgramacion(), v.getFecha(), v.getIdServicio(), v.getNombreServicio(),
            v.getNombreVeterinario(), v.getNombreTurno(), v.getHoraInicio(), v.getHoraFin()
        )).toList();
    }

    @Override
    public List<String> horasOcupadas(Integer idProgramacion, String fecha, Integer excluirId) {
        return r.findHorasOcupadas(idProgramacion, fecha, excluirId);
    }
}
