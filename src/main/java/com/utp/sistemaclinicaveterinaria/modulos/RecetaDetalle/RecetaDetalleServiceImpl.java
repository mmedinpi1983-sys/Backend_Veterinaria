package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.ListItem;
@Service
public class RecetaDetalleServiceImpl implements RecetaDetalleService {
    private final RecetaDetalleRepository repository;
    public RecetaDetalleServiceImpl(RecetaDetalleRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdRecetaDetalle(), e.getDosis(), e.getFrecuencia(), e.getDuracion(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        RecetaDetalle e = repository.findByIdRecetaDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RecetaDetalle no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        RecetaDetalle e = new RecetaDetalle();
        e.setIdReceta(request.idReceta());
        e.setIdMedicamento(request.idMedicamento());
        e.setDosis(request.dosis());
        e.setFrecuencia(request.frecuencia());
        e.setDuracion(request.duracion());
        e.setViaAdministracion(request.viaAdministracion());
        e.setIndicacionesEspecificas(request.indicacionesEspecificas());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        RecetaDetalle e = repository.findByIdRecetaDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RecetaDetalle no encontrado", "NOT_FOUND"));
        e.setIdReceta(request.idReceta());
        e.setIdMedicamento(request.idMedicamento());
        e.setDosis(request.dosis());
        e.setFrecuencia(request.frecuencia());
        e.setDuracion(request.duracion());
        e.setViaAdministracion(request.viaAdministracion());
        e.setIndicacionesEspecificas(request.indicacionesEspecificas());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        RecetaDetalle e = repository.findByIdRecetaDetalleAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("RecetaDetalle no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(RecetaDetalle e) { return new Response(e.getIdRecetaDetalle(), e.getIdReceta(), e.getIdMedicamento(), e.getDosis(), e.getFrecuencia(), e.getDuracion(), e.getViaAdministracion(), e.getIndicacionesEspecificas(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
