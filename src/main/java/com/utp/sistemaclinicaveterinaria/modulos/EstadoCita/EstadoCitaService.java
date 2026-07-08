package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaUpdateRequest;

public interface EstadoCitaService {
    List<EstadoCitaCatalogResponse> catalogo();
    List<EstadoCitaListResponse> listar(EstadoCitaFilterRequest f);
    EstadoCitaDetailResponse obtenerId(Integer id);
    void crear(EstadoCitaCreateRequest c);
    void actualizar(Integer id, EstadoCitaUpdateRequest mt);
    void eliminar(EstadoCitaDeleteRequest e);
}
