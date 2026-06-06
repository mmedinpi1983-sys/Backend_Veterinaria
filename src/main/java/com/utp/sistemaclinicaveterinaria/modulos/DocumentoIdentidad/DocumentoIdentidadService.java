package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.Request;
public interface DocumentoIdentidadService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
