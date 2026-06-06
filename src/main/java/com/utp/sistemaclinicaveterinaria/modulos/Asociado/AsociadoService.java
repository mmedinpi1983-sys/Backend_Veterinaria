package com.utp.sistemaclinicaveterinaria.modulos.Asociado;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.Request;
public interface AsociadoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
