package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.ListItem;
@Service
public class EspecieRazaServiceImpl implements EspecieRazaService {
    private final EspecieRazaRepository repository;
    public EspecieRazaServiceImpl(EspecieRazaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdEspecieRaza(), e.getNombre(), e.getIdEspecie(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        EspecieRaza e = repository.findByIdEspecieRazaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EspecieRaza no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        EspecieRaza e = new EspecieRaza();
        e.setNombre(request.nombre());
        e.setIdEspecie(request.idEspecie());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        EspecieRaza e = repository.findByIdEspecieRazaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EspecieRaza no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setIdEspecie(request.idEspecie());
        e.setEstado(request.estado());
        e.setIdAsociado(request.idAsociado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        EspecieRaza e = repository.findByIdEspecieRazaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("EspecieRaza no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(EspecieRaza e) { return new Response(e.getIdEspecieRaza(), e.getNombre(), e.getIdEspecie(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdAsociado(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
