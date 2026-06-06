package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.Request;
public interface NivelSuscripcionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
