package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.Request;
public interface EstadoSalidaAtencionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
