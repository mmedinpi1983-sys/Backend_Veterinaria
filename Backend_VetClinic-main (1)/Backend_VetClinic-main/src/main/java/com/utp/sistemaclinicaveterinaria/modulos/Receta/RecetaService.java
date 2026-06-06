package com.utp.sistemaclinicaveterinaria.modulos.Receta;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.Request;
public interface RecetaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
