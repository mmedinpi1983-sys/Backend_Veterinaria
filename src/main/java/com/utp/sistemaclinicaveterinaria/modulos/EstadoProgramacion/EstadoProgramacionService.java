package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionUpdateRequest;

public interface EstadoProgramacionService {
    List<EstadoProgramacionCatalogResponse> catalogo();
    List<EstadoProgramacionListResponse> listar(EstadoProgramacionFilterRequest f);
    EstadoProgramacionDetailResponse obtenerId(Integer id);
    void crear(EstadoProgramacionCreateRequest c);
    void actualizar(Integer id, EstadoProgramacionUpdateRequest mt);
    void eliminar(EstadoProgramacionDeleteRequest e);
}
