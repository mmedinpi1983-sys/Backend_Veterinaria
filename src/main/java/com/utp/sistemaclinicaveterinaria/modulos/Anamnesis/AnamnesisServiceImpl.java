package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.ListItem;
@Service
public class AnamnesisServiceImpl implements AnamnesisService {
    private final AnamnesisRepository repository;
    public AnamnesisServiceImpl(AnamnesisRepository repository) { this.repository = repository; }
    @Override public List<ListItem> listar() { return repository.findByFechaEliminacionIsNull().stream().map(e -> new ListItem(e.getIdAnamnesis(), e.getAntecedentes(), e.getMedicamentosActuales(), e.getAlimentacion(), e.getFechaCreacion())).toList(); }
    @Override public Response obtenerPorId(Integer id) {
        Anamnesis e = repository.findByIdAnamnesisAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Anamnesis no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }
    @Override public Response crear(Request request) {
        Anamnesis e = new Anamnesis();
        e.setIdConsulta(request.idConsulta());
        e.setAntecedentes(request.antecedentes());
        e.setAlergias(request.alergias());
        e.setCirugiasAnteriores(request.cirugiasAnteriores());
        e.setMedicamentosActuales(request.medicamentosActuales());
        e.setAlimentacion(request.alimentacion());
        e.setComportamiento(request.comportamiento());
        e.setInicioSintomas(request.inicioSintomas());
        e.setEvolucionSintomas(request.evolucionSintomas());
        e.setObservaciones(request.observaciones());
        e.setDetalleAlergias(request.detalleAlergias());
        e.setDetalleCirugias(request.detalleCirugias());
        e.setHistorialVacunacion(request.historialVacunacion());
        e.setEstiloVida(request.estiloVida());
        e.setHistorialReproductivo(request.historialReproductivo());
        e.setReproduccionDetalle(request.reproduccionDetalle());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(1);
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public Response actualizar(Integer id, Request request) {
        Anamnesis e = repository.findByIdAnamnesisAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Anamnesis no encontrado", "NOT_FOUND"));
        e.setIdConsulta(request.idConsulta());
        e.setAntecedentes(request.antecedentes());
        e.setAlergias(request.alergias());
        e.setCirugiasAnteriores(request.cirugiasAnteriores());
        e.setMedicamentosActuales(request.medicamentosActuales());
        e.setAlimentacion(request.alimentacion());
        e.setComportamiento(request.comportamiento());
        e.setInicioSintomas(request.inicioSintomas());
        e.setEvolucionSintomas(request.evolucionSintomas());
        e.setObservaciones(request.observaciones());
        e.setDetalleAlergias(request.detalleAlergias());
        e.setDetalleCirugias(request.detalleCirugias());
        e.setHistorialVacunacion(request.historialVacunacion());
        e.setEstiloVida(request.estiloVida());
        e.setHistorialReproductivo(request.historialReproductivo());
        e.setReproduccionDetalle(request.reproduccionDetalle());
        e.setFechaModificacion(LocalDateTime.now());
        e = repository.save(e);
        return toResponse(e);
    }
    @Override public void eliminar(Integer id) {
        Anamnesis e = repository.findByIdAnamnesisAndFechaEliminacionIsNull(id).orElseThrow(() -> new ApiException("Anamnesis no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        repository.save(e);
    }
    private Response toResponse(Anamnesis e) { return new Response(e.getIdAnamnesis(), e.getIdConsulta(), e.getAntecedentes(), e.getAlergias(), e.getCirugiasAnteriores(), e.getMedicamentosActuales(), e.getAlimentacion(), e.getComportamiento(), e.getInicioSintomas(), e.getEvolucionSintomas(), e.getObservaciones(), e.getDetalleAlergias(), e.getDetalleCirugias(), e.getHistorialVacunacion(), e.getEstiloVida(), e.getHistorialReproductivo(), e.getReproduccionDetalle(), e.getFechaCreacion(), e.getFechaModificacion(), e.getFechaEliminacion(), e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador()); }
}
