package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionUpdateRequest;

public interface AtencionVacunacionService {
    List<AtencionVacunacionListResponse> listar();
    AtencionVacunacionDetailResponse obtenerId(Integer idVacunacion);
    void crear(AtencionVacunacionCreateRequest c);
    void actualizar(Integer idVacunacion, AtencionVacunacionUpdateRequest mt);
    void eliminar(AtencionVacunacionDeleteRequest e);
}
