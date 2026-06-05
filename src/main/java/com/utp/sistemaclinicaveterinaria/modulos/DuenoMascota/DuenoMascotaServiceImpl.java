package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.ListItem;
@Service
public class DuenoMascotaServiceImpl implements DuenoMascotaService {
    private final DuenoMascotaRepository repository;
    public DuenoMascotaServiceImpl(DuenoMascotaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdDuenoMascota(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        DuenoMascota e = repository.findByIdDuenoMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("DuenoMascota no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        DuenoMascota e = new DuenoMascota();
        e.setIdDueno(request.idDueno());
        e.setIdMascota(request.idMascota());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        DuenoMascota e = repository.findByIdDuenoMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("DuenoMascota no encontrado", "NOT_FOUND"));
        e.setIdDueno(request.idDueno());
        e.setIdMascota(request.idMascota());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        DuenoMascota e = repository.findByIdDuenoMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("DuenoMascota no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(DuenoMascota e) { return new Response(e.getIdDuenoMascota(), e.getIdDueno(), e.getIdMascota(), e.getIdAsociado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
