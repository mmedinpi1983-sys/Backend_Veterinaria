package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.Request;
public interface RecetaDetalleService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
