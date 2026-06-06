package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.Request;
public interface TriajeService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
