package com.utp.sistemaclinicaveterinaria.modulos.Programacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.Request;
public interface ProgramacionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
