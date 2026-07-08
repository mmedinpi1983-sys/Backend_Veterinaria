package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaUpdateRequest;

public interface AtencionEsteticaService {
    List<AtencionEsteticaListResponse> listar();
    AtencionEsteticaDetailResponse obtenerId(Integer idEstetica);
    void crear(AtencionEsteticaCreateRequest c);
    void actualizar(Integer idEstetica, AtencionEsteticaUpdateRequest mt);
    void eliminar(AtencionEsteticaDeleteRequest e);
}
