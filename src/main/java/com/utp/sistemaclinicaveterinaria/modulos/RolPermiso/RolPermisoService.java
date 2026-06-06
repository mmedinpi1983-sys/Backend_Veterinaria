package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.Request;
public interface RolPermisoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
