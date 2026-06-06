package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.Request;
public interface MetodoIngresoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
