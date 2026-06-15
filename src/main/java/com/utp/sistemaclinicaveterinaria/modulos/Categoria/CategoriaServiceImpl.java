package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.ListItem;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repository;
    public CategoriaServiceImpl(CategoriaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdCategoria(), e.getNombreCategoria(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Categoria e = repository.findByIdCategoriaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Categoria no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Categoria e = new Categoria();
        e.setNombreCategoria(request.nombreCategoria());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Categoria e = repository.findByIdCategoriaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Categoria no encontrado", "NOT_FOUND"));
        e.setNombreCategoria(request.nombreCategoria());
        e.setIdAsociado(request.idAsociado());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Categoria e = repository.findByIdCategoriaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Categoria no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Categoria e) { return new Response(e.getIdCategoria(), e.getNombreCategoria(), e.getIdAsociado(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
