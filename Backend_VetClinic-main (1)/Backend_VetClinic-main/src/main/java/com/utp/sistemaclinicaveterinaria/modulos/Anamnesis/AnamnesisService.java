package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.Request;
public interface AnamnesisService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
