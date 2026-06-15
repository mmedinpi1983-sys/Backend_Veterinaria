package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Anamnesis;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AnamnesisInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.ConsultaInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.MedicamentoReceta;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.RecetaInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.TriajeInfo;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsulta;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Receta;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaRepository;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Triaje;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeRepository;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalle;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleRepository;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;

@Service
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository repository;
    private final TriajeRepository triajeRepository;
    private final TriajeDetalleRepository triajeDetalleRepository;
    private final AtencionConsultaRepository consultaRepository;
    private final AnamnesisRepository anamnesisRepository;
    private final RecetaRepository recetaRepository;
    private final RecetaDetalleRepository recetaDetalleRepository;

    public AtencionServiceImpl(
            AtencionRepository repository,
            TriajeRepository triajeRepository,
            TriajeDetalleRepository triajeDetalleRepository,
            AtencionConsultaRepository consultaRepository,
            AnamnesisRepository anamnesisRepository,
            RecetaRepository recetaRepository,
            RecetaDetalleRepository recetaDetalleRepository) {
        this.repository = repository;
        this.triajeRepository = triajeRepository;
        this.triajeDetalleRepository = triajeDetalleRepository;
        this.consultaRepository = consultaRepository;
        this.anamnesisRepository = anamnesisRepository;
        this.recetaRepository = recetaRepository;
        this.recetaDetalleRepository = recetaDetalleRepository;
    }

    @Override
    public List<ListItem> listar() {
        return repository.findByFechaEliminacionIsNull().stream()
                .map(e -> new ListItem(e.getIdAtencion(), e.getObservacion(), e.getFechaCreacion()))
                .toList();
    }

    @Override
    public Response obtenerPorId(Integer id) {
        Atencion e = repository.findByIdAtencionAndFechaEliminacionIsNull(id)
                .orElseThrow(() -> new ApiException("Atencion no encontrado", "NOT_FOUND"));
        return toResponse(e);
    }

    @Override
    public Response obtenerPorCita(Integer idCita) {
        Atencion e = repository.findByIdCitaProgramadaAndFechaEliminacionIsNull(idCita)
                .orElseThrow(() -> new ApiException("No existe atención para esta cita", "NOT_FOUND"));
        return toResponse(e);
    }

    @Override
    public DetalleCompleto obtenerDetalle(Integer id) {
        Atencion atencion = repository.findByIdAtencionAndFechaEliminacionIsNull(id)
                .orElseThrow(() -> new ApiException("Atencion no encontrado", "NOT_FOUND"));

        TriajeInfo triajeInfo = null;
        if (atencion.getIdTriaje() != null) {
            Triaje t = triajeRepository.findByIdTriajeAndFechaEliminacionIsNull(atencion.getIdTriaje()).orElse(null);
            if (t != null) {
                TriajeDetalle td = triajeDetalleRepository
                        .findByIdTriajeAndFechaEliminacionIsNull(t.getIdTriaje()).orElse(null);
                triajeInfo = new TriajeInfo(
                        t.getIdTriaje(), t.getCodigoTemporal(), t.getPrioridad(), t.getIdMetodoIngreso(),
                        td != null ? td.getTemperatura() : null,
                        td != null ? td.getPeso() : null,
                        td != null ? td.getAlergias() : null,
                        td != null ? td.getObservaciones() : null);
            }
        }

        ConsultaInfo consultaInfo = null;
        AnamnesisInfo anamnesisInfo = null;
        RecetaInfo recetaInfo = null;

        AtencionConsulta consulta = consultaRepository
                .findByIdAtencionAndFechaEliminacionIsNull(atencion.getIdAtencion()).orElse(null);

        if (consulta != null) {
            consultaInfo = new ConsultaInfo(
                    consulta.getIdConsulta(), consulta.getMotivoConsulta(), consulta.getEvaluacionClinica(),
                    consulta.getTratamiento(), consulta.getIndicaciones(), consulta.getObservaciones(),
                    consulta.getRequiereControl(), consulta.getFechaProximoControl());

            Anamnesis anamnesis = anamnesisRepository
                    .findByIdConsultaAndFechaEliminacionIsNull(consulta.getIdConsulta()).orElse(null);
            if (anamnesis != null) {
                anamnesisInfo = new AnamnesisInfo(
                        anamnesis.getIdAnamnesis(), anamnesis.getAntecedentes(), anamnesis.getAlergias(),
                        anamnesis.getDetalleAlergias(), anamnesis.getCirugiasAnteriores(),
                        anamnesis.getDetalleCirugias(), anamnesis.getMedicamentosActuales(),
                        anamnesis.getHistorialVacunacion(), anamnesis.getAlimentacion(),
                        anamnesis.getComportamiento(), anamnesis.getHistorialReproductivo(),
                        anamnesis.getInicioSintomas(), anamnesis.getEvolucionSintomas(),
                        anamnesis.getObservaciones());
            }

            Receta receta = recetaRepository
                    .findByIdConsultaAndFechaEliminacionIsNull(consulta.getIdConsulta()).orElse(null);
            if (receta != null) {
                List<MedicamentoReceta> detalle = recetaDetalleRepository
                        .findByIdRecetaAndFechaEliminacionIsNull(receta.getIdReceta())
                        .stream().map(rd -> new MedicamentoReceta(
                                rd.getIdRecetaDetalle(), rd.getIdMedicamento(),
                                rd.getDosis(), rd.getFrecuencia(), rd.getDuracion(),
                                rd.getIndicacionesEspecificas()))
                        .toList();
                recetaInfo = new RecetaInfo(receta.getIdReceta(), receta.getFechaReceta(), detalle);
            }
        }

        return new DetalleCompleto(toResponse(atencion), triajeInfo, consultaInfo, anamnesisInfo, recetaInfo);
    }

    @Override
    public Response crear(Request request) {
        Atencion e = new Atencion();
        e.setIdCitaProgramada(request.idCitaProgramada());
        e.setIdTriaje(request.idTriaje());
        e.setIdAsociado(request.idAsociado());
        e.setFechaAtencion(request.fechaAtencion());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setObservacion(request.observacion());
        e.setIdEstadoSalida(request.idEstadoSalida());
        e.setIdEstadoAtencion(request.idEstadoAtencion());
        e.setIdMascota(request.idMascota());
        e.setFechaCreacion(LocalDateTime.now());
        e.setIdEmpleadoCreador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }

    @Override
    public Response actualizar(Integer id, Request request) {
        Atencion e = repository.findByIdAtencionAndFechaEliminacionIsNull(id)
                .orElseThrow(() -> new ApiException("Atencion no encontrado", "NOT_FOUND"));
        e.setIdCitaProgramada(request.idCitaProgramada());
        e.setIdTriaje(request.idTriaje());
        e.setIdAsociado(request.idAsociado());
        e.setFechaAtencion(request.fechaAtencion());
        e.setHoraInicio(request.horaInicio());
        e.setHoraFin(request.horaFin());
        e.setObservacion(request.observacion());
        e.setIdEstadoSalida(request.idEstadoSalida());
        e.setIdEstadoAtencion(request.idEstadoAtencion());
        e.setIdMascota(request.idMascota());
        e.setFechaModificacion(LocalDateTime.now());
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e = repository.save(e);
        return toResponse(e);
    }

    @Override
    public void eliminar(Integer id) {
        Atencion e = repository.findByIdAtencionAndFechaEliminacionIsNull(id)
                .orElseThrow(() -> new ApiException("Atencion no encontrado", "NOT_FOUND"));
        e.setFechaEliminacion(LocalDateTime.now());
        e.setIdEmpleadoEliminador(UsuarioActual.getId());
        repository.save(e);
    }

    @Override
    public List<HistorialView> getHistorialByMascota(Integer idMascota) {
        return repository.findHistorialByIdMascota(idMascota);
    }

    private Response toResponse(Atencion e) {
        return new Response(e.getIdAtencion(), e.getIdCitaProgramada(), e.getIdTriaje(), e.getIdAsociado(),
                e.getFechaAtencion(), e.getHoraInicio(), e.getHoraFin(), e.getObservacion(),
                e.getIdEstadoSalida(), e.getFechaCreacion(), e.getFechaModificacion(),
                e.getFechaEliminacion(), e.getIdEstadoAtencion(), e.getIdMascota(),
                e.getIdEmpleadoCreador(), e.getIdEmpleadoModificador(), e.getIdEmpleadoEliminador());
    }
}
