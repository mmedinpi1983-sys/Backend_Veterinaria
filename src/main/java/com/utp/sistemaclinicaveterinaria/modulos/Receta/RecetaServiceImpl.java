package com.utp.sistemaclinicaveterinaria.modulos.Receta;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.ListItem;
@Service
public class RecetaServiceImpl implements RecetaService {
    private final RecetaRepository repository;
    public RecetaServiceImpl(RecetaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdReceta(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Receta e = repository.findByIdRecetaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Receta no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Receta e = new Receta();
        e.setIdConsulta(request.idConsulta());
        e.setFechaReceta(request.fechaReceta());
        e.setIdEmpleadoAsociado(request.idEmpleadoAsociado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Receta e = repository.findByIdRecetaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Receta no encontrado", "NOT_FOUND"));
        e.setIdConsulta(request.idConsulta());
        e.setFechaReceta(request.fechaReceta());
        e.setIdEmpleadoAsociado(request.idEmpleadoAsociado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Receta e = repository.findByIdRecetaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Receta no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        e.setIdEmpleadoEliminador(UsuarioActual.getId());
        repository.save(e);
    }
    private Response toResponse(Receta e) { return new Response(e.getIdReceta(), e.getIdConsulta(), e.getFechaReceta(), e.getIdEmpleadoAsociado(), e.getIdAsociado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
