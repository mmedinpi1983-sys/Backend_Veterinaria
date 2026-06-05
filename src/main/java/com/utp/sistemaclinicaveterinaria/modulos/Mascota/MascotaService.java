package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.Request;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;
public interface MascotaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
    List<SearchItem> buscar(String q);
}
