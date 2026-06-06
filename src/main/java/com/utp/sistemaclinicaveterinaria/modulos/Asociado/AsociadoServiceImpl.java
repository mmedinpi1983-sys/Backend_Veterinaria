package com.utp.sistemaclinicaveterinaria.modulos.Asociado;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.ListItem;
@Service
public class AsociadoServiceImpl implements AsociadoService {
    private final AsociadoRepository repository;
    public AsociadoServiceImpl(AsociadoRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdAsociado(), e.getNombre(), e.getRuc(), e.getNombreDueno(), e.getFechaCreacion(), e.getActivo())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Asociado e = repository.findByIdAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Asociado no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Asociado e = new Asociado();
        e.setNombre(request.nombre());
        e.setRuc(request.ruc());
        e.setNombreDueno(request.nombreDueno());
        e.setApellidoDueno(request.apellidoDueno());
        e.setIdNivelSuscripcion(request.idNivelSuscripcion());
        e.setActivo(request.activo());
        e.setCorreoElectronico(request.correoElectronico());
        e.setNroTelefono(request.nroTelefono());
        e.setDireccion(request.direccion());
        e.setDiasAtencion(request.diasAtencion());
        e.setFechaCreacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Asociado e = repository.findByIdAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Asociado no encontrado", "NOT_FOUND"));
        e.setNombre(request.nombre());
        e.setRuc(request.ruc());
        e.setNombreDueno(request.nombreDueno());
        e.setApellidoDueno(request.apellidoDueno());
        e.setIdNivelSuscripcion(request.idNivelSuscripcion());
        e.setActivo(request.activo());
        e.setCorreoElectronico(request.correoElectronico());
        e.setNroTelefono(request.nroTelefono());
        e.setDireccion(request.direccion());
        e.setDiasAtencion(request.diasAtencion());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Asociado e = repository.findByIdAsociadoAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Asociado no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Asociado e) { return new Response(e.getIdAsociado(), e.getNombre(), e.getRuc(), e.getNombreDueno(), e.getApellidoDueno(), e.getIdNivelSuscripcion(), e.getActivo(), e.getCorreoElectronico(), e.getNroTelefono(), e.getDireccion(), e.getDiasAtencion(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdSuperAdminCreador(), e.getIdSuperAdminModificador(), e.getIdSuperAdminEliminador()); }
}
