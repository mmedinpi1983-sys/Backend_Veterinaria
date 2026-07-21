package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.VeterinarioDisponible;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ProgramacionCita;

public interface CitaProgramadaService {
    List<CitaProgramadaListResponse> listar();
    CitaProgramadaDetailResponse obtenerId(Integer idCitaProgramada);
    void crear(CitaProgramadaCreateRequest c);
    void actualizar(Integer idCitaProgramada, CitaProgramadaUpdateRequest mt);
    void eliminar(CitaProgramadaDeleteRequest e);
    List<CitaEnriquecida> listarEnriquecido(Integer idEstado, Integer idEmpleado, String fechaInicio, String fechaFin, String busqueda);
    StatsResponse getStats();
    List<CitaEnriquecida> listarPorMascota(Integer idMascota);
    List<VeterinarioDisponible> listarVeterinarios();
    List<ProgramacionCita> listarProgramacionesCita();
    List<String> horasOcupadas(Integer idProgramacion, String fecha, Integer excluirId);
}
