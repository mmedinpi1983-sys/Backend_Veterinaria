package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.Request;
public interface MedicamentoCatalogoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
