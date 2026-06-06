package com.utp.sistemaclinicaveterinaria.modulos.Atencion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;
public interface AtencionService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response obtenerPorCita(Integer idCita);
    DetalleCompleto obtenerDetalle(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
    List<HistorialView> getHistorialByMascota(Integer idMascota);
}
