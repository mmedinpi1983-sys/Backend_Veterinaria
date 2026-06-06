package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.Request;
public interface EmpleadoAsociadoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
