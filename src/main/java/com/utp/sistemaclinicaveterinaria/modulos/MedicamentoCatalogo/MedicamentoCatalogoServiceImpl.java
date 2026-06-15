package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;
import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.ListItem;
@Service
public class MedicamentoCatalogoServiceImpl implements MedicamentoCatalogoService {
    private final MedicamentoCatalogoRepository repository;
    public MedicamentoCatalogoServiceImpl(MedicamentoCatalogoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findAll().stream().map(e -> new ListItem(e.getIdMedicamento(), e.getCodigoMedicamento(), e.getNombreMedicamento(), e.getDescripcion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        MedicamentoCatalogo e = repository.findById(id).orElseThrow(() -> new ApiException("MedicamentoCatalogo no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        MedicamentoCatalogo e = new MedicamentoCatalogo();
        e.setCodigoMedicamento(request.codigoMedicamento());
        e.setNombreMedicamento(request.nombreMedicamento());
        e.setDescripcion(request.descripcion());
        e.setConcentracion(request.concentracion());
        e.setPresentacion(request.presentacion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        MedicamentoCatalogo e = repository.findById(id).orElseThrow(() -> new ApiException("MedicamentoCatalogo no encontrado", "NOT_FOUND"));
        e.setCodigoMedicamento(request.codigoMedicamento());
        e.setNombreMedicamento(request.nombreMedicamento());
        e.setDescripcion(request.descripcion());
        e.setConcentracion(request.concentracion());
        e.setPresentacion(request.presentacion());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        MedicamentoCatalogo e = repository.findById(id).orElseThrow(() -> new ApiException("MedicamentoCatalogo no encontrado", "NOT_FOUND"));
        repository.delete(e);
    }
    private Response toResponse(MedicamentoCatalogo e) { return new Response(e.getIdMedicamento(), e.getCodigoMedicamento(), e.getNombreMedicamento(), e.getDescripcion(), e.getConcentracion(), e.getPresentacion()); }
}
