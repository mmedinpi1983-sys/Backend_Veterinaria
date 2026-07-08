package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionUpdateRequest;

public interface PresentacionService {
    List<PresentacionCatalogResponse> catalogo(Integer idAsociado);
    List<PresentacionListResponse> listar(PresentacionFilterRequest f);
    PresentacionDetailResponse obtenerId(Integer idPresentacion, Integer idAsociado);
    void crear(PresentacionCreateRequest c);
    void actualizar(Integer idPresentacion, PresentacionUpdateRequest mt);
    void eliminar(PresentacionDeleteRequest e);
}
