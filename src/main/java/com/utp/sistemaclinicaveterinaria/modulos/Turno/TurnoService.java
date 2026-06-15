package com.utp.sistemaclinicaveterinaria.modulos.Turno;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.Request;
public interface TurnoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
