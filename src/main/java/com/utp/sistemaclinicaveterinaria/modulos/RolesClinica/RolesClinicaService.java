package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.ListItem;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.Response;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.Request;
public interface RolesClinicaService {
    List<ListItem> listar();
    Response obtenerPorId(Integer id);
    Response crear(Request request);
    Response actualizar(Integer id, Request request);
    void eliminar(Integer id);
}
