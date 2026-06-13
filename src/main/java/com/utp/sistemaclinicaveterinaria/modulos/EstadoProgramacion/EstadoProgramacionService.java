package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.Request;
public interface EstadoProgramacionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
