package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionUpdateRequest;

public interface EstadoSalidaAtencionService {
    List<EstadoSalidaAtencionCatalogResponse> catalogo();
    List<EstadoSalidaAtencionListResponse> listar(EstadoSalidaAtencionFilterRequest f);
    EstadoSalidaAtencionDetailResponse obtenerId(Integer id);
    void crear(EstadoSalidaAtencionCreateRequest c);
    void actualizar(Integer id, EstadoSalidaAtencionUpdateRequest mt);
    void eliminar(EstadoSalidaAtencionDeleteRequest e);
}
