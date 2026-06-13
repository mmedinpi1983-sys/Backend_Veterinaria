package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.Request;
public interface EstadoCitaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
