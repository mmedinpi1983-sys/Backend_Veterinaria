package com.utp.sistemaclinicaveterinaria.modulos.Servicio;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.Request;
public interface ServicioService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
