package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.Request;
public interface NivelPermisoService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
