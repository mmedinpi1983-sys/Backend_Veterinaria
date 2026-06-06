package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.Request;
public interface PermisoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
