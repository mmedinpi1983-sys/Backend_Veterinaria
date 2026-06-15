package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.Request;
public interface CategoriaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
