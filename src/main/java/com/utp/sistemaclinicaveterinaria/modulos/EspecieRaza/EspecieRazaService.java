package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRazaDTO.Request;
public interface EspecieRazaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
