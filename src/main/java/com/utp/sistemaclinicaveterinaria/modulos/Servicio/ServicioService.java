package com.utp.sistemaclinicaveterinaria.modulos.Servicio;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioUpdateRequest;

public interface ServicioService {
    List<ServicioCatalogResponse> catalogo(Integer idAsociado);
    List<ServicioListResponse> listar(ServicioFilterRequest f);
    ServicioDetailResponse obtenerId(Integer idServicio, Integer idAsociado);
    void crear(ServicioCreateRequest c);
    void actualizar(Integer idServicio, ServicioUpdateRequest mt);
    void eliminar(ServicioDeleteRequest e);
}
