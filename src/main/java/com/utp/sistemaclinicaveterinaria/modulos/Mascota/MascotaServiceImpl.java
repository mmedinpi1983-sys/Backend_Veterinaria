package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;
@Service
public class MascotaServiceImpl implements MascotaService {
    private final MascotaRepository repository;
    public MascotaServiceImpl(MascotaRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdMascota(), e.getNombre(), e.getSexo(), e.getTamanio(), e.getFechaCreacion(), e.getEstado())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Mascota e = repository.findByIdMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Mascota no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Mascota e = new Mascota();
        e.setNombre(request.nombre());
        e.setIdEspecie(request.idEspecie());
        e.setIdRaza(request.idRaza());
        e.setIdAsociado(request.idAsociado());
        e.setFechaNacimiento(request.fechaNacimiento());
        e.setSexo(request.sexo());
        e.setTamanio(request.tamanio());
        e.setNotas(request.notas());
        e.setEstado(request.estado());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Mascota e = repository.findByIdMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Mascota no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setIdEspecie(request.idEspecie());
        e.setIdRaza(request.idRaza());
        e.setIdAsociado(request.idAsociado());
        e.setFechaNacimiento(request.fechaNacimiento());
        e.setSexo(request.sexo());
        e.setTamanio(request.tamanio());
        e.setNotas(request.notas());
        e.setEstado(request.estado());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Mascota e = repository.findByIdMascotaAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Mascota no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    @Override public List<SearchItem> buscar(String q) {
        return repository.buscarConDueno(q == null || q.isBlank() ? null : q).stream()
            .map(v -> new SearchItem(v.getIdMascota(), v.getNombre(), v.getEspecie(), v.getRaza(),
                    v.getIdDueno(), v.getNombreDueno(), v.getTamanio(), v.getSexo()))
            .toList();
    }
    private Response toResponse(Mascota e) { return new Response(e.getIdMascota(), e.getNombre(), e.getIdEspecie(), e.getIdRaza(), e.getIdAsociado(), e.getFechaNacimiento(), e.getSexo(), e.getTamanio(), e.getNotas(), e.getEstado(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
