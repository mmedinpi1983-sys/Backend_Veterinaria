package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionUpdateRequest;

public interface EstadoAtencionService {
    List<EstadoAtencionCatalogResponse> catalogo();
    List<EstadoAtencionListResponse> listar(EstadoAtencionFilterRequest f);
    EstadoAtencionDetailResponse obtenerId(Integer id);
    void crear(EstadoAtencionCreateRequest c);
    void actualizar(Integer id, EstadoAtencionUpdateRequest mt);
    void eliminar(EstadoAtencionDeleteRequest e);
}
