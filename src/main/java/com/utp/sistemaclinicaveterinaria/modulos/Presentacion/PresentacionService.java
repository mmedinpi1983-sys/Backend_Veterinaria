package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.Request;
public interface PresentacionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
