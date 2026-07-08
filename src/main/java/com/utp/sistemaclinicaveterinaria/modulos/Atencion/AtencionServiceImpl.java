package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AnamnesisInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.ConsultaInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.MedicamentoReceta;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.RecetaInfo;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.TriajeInfo;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsulta;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Anamnesis;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Receta;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaRepository;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Triaje;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeRepository;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalle;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleRepository;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository r;
    private final AtencionMapper m;
    private final TriajeRepository triajeRepository;
    private final TriajeDetalleRepository triajeDetalleRepository;
    private final AtencionConsultaRepository consultaRepository;
    private final AnamnesisRepository anamnesisRepository;
    private final RecetaRepository recetaRepository;
    private final RecetaDetalleRepository recetaDetalleRepository;

    public AtencionServiceImpl(
            AtencionRepository r,
            AtencionMapper m,
            TriajeRepository triajeRepository,
            TriajeDetalleRepository triajeDetalleRepository,
            AtencionConsultaRepository consultaRepository,
            AnamnesisRepository anamnesisRepository,
            RecetaRepository recetaRepository,
            RecetaDetalleRepository recetaDetalleRepository) {
        this.r = r;
        this.m = m;
        this.triajeRepository = triajeRepository;
        this.triajeDetalleRepository = triajeDetalleRepository;
        this.consultaRepository = consultaRepository;
        this.anamnesisRepository = anamnesisRepository;
        this.recetaRepository = recetaRepository;
        this.recetaDetalleRepository = recetaDetalleRepository;
    }

    @Override
    public List<AtencionListResponse> listar() {
        return m.AtencionListMapperList(r.listar());
    }

    @Override
    public AtencionDetailResponse obtenerId(Integer idAtencion) {
        return m.AtencionDetailMapper(r.detalle(idAtencion));
    }

    @Override
    public AtencionDetailResponse obtenerPorCita(Integer idCita) {
        Atencion entity = r.findByIdCitaProgramadaAndFechaEliminacionIsNull(idCita)
                .orElseThrow(() -> new ApiException("No existe atención para esta cita", "NOT_FOUND"));
        return m.AtencionDetailMapper(r.detalle(entity.getIdAtencion()));
    }

    @Override
    public DetalleCompleto obtenerDetalle(Integer id) {
        AtencionDetailResponse atencion = m.AtencionDetailMapper(r.detalle(id));

        Atencion entity = r.findByIdCitaProgramadaAndFechaEliminacionIsNull(
            r.getReferenceById(id).getIdCitaProgramada()).orElse(null);

        TriajeInfo triajeInfo = null;
        if (entity != null && entity.getIdTriaje() != null) {
            Triaje t = triajeRepository.findByIdTriajeAndFechaEliminacionIsNull(entity.getIdTriaje()).orElse(null);
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
                .findByIdAtencionAndFechaEliminacionIsNull(id).orElse(null);

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

        return new DetalleCompleto(atencion, triajeInfo, consultaInfo, anamnesisInfo, recetaInfo);
    }

    @Override
    public void crear(AtencionCreateRequest c) {
        Atencion entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer idAtencion, AtencionUpdateRequest mt) {
        Atencion entity = r.getReferenceById(idAtencion);
        m.updateEntity(entity, mt);
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(AtencionDeleteRequest e) {
        r.eliminar(e.idAtencion(), UsuarioActual.getId());
    }

    @Override
    public List<HistorialView> getHistorialByMascota(Integer idMascota) {
        return r.findHistorialByIdMascota(idMascota);
    }
}
