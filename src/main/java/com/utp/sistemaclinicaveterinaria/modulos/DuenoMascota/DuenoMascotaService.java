package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.Request;
public interface DuenoMascotaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
