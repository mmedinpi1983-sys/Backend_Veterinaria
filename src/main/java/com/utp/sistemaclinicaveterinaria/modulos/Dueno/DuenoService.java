package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.Request;
public interface DuenoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
