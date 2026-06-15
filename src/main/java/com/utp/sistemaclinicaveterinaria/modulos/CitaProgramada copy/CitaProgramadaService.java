package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaEnriquecida;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.StatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.Request;
public interface CitaProgramadaService {
    List<ListItem> listar();
    List<CitaEnriquecida> listarEnriquecido(Integer idEstado, Integer idEmpleado, String fechaInicio, String fechaFin, String busqueda);
    StatsResponse getStats();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
    List<CitaEnriquecida> listarPorMascota(Integer idMascota);
}
