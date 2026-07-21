package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaRepository;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Consultorio;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioRepository;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

import java.time.LocalDateTime;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionUpdateRequest;

@Service
public class ProgramacionServiceImpl implements ProgramacionService {
    private final ProgramacionRepository r;
    private final ProgramacionMapper m;
    private final ConsultorioRepository consultorioRepo;
    private final CitaProgramadaRepository citaRepo;

    public ProgramacionServiceImpl(ProgramacionRepository r, ProgramacionMapper m,
            ConsultorioRepository consultorioRepo, CitaProgramadaRepository citaRepo) {
        this.r = r;
        this.m = m;
        this.consultorioRepo = consultorioRepo;
        this.citaRepo = citaRepo;
    }

    // Copia el nombre del consultorio en 'ambiente' (snapshot histórico) cuando se
    // asigna/cambia el consultorio. Si no se envía consultorio, deja 'ambiente' como esté.
    private void aplicarSnapshotConsultorio(Programacion entity, Integer idConsultorio) {
        if (idConsultorio == null) {
            return;
        }
        Consultorio c = consultorioRepo.findById(idConsultorio).orElse(null);
        if (c != null) {
            String piso = c.getPiso();
            String etiqueta = (piso != null && !piso.isBlank())
                    ? c.getNombre() + " - " + piso
                    : c.getNombre();
            if (etiqueta.length() > 255) {
                etiqueta = etiqueta.substring(0, 255);
            }
            entity.setAmbiente(etiqueta);
        }
    }

    @Override
    public List<ProgramacionCatalogResponse> catalogo(Integer idAsociado) {
        return m.toCatalogResponseList(r.catalogo(idAsociado));
    }

    @Override
    public List<ProgramacionListResponse> listar(ProgramacionFilterRequest f) {
        String fecha = f == null || f.fecha() == null ? "" : f.fecha().trim();
        Integer idEmpleadoRegistrador = f == null || f.idEmpleadoRegistrador() == null ? 0 : f.idEmpleadoRegistrador();
        Integer idEstadoProgramacion = f == null || f.idEstadoProgramacion() == null ? 0 : f.idEstadoProgramacion();
        Integer idTurno = f == null || f.idTurno() == null ? 0 : f.idTurno();
        return m.toListResponseList(
                r.listar(UsuarioActual.getAsociadoId(), fecha, idEmpleadoRegistrador, idEstadoProgramacion, idTurno));
    }

    @Override
    public ProgramacionDetailResponse obtenerId(Integer id, Integer idAsociado) {
        return m.toDetailResponse(r.detalle(id, idAsociado));
    }

    @Override
    public void crear(ProgramacionCreateRequest c) {
        Programacion entity = m.toEntity(c);
        aplicarSnapshotConsultorio(entity, c.idConsultorio());
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setIdEmpleadoCreador(UsuarioActual.getId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, ProgramacionUpdateRequest t) {
        Programacion entity = r.getReferenceById(id);
        m.updateEntity(entity, t);
        aplicarSnapshotConsultorio(entity, t.idConsultorio());
        entity.setIdEmpleadoModificador(UsuarioActual.getId());
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void eliminar(ProgramacionDeleteRequest e) {
        long citas = citaRepo.contarPorProgramacion(e.idProgramacion());
        if (citas > 0) {
            throw new ApiException(
                    "No se puede eliminar la programación porque tiene " + citas + " cita(s) asociada(s).",
                    "PROGRAMACION_CON_CITAS");
        }
        r.eliminar(e.idProgramacion(), UsuarioActual.getId(), UsuarioActual.getAsociadoId());
    }
}
