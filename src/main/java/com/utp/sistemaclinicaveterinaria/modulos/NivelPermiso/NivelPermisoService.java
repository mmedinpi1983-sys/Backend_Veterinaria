package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoUpdateRequest;

public interface NivelPermisoService {
    List<NivelPermisoCatalogResponse> catalogo();
    List<NivelPermisoListResponse> listar();
    NivelPermisoDetailResponse obtenerId(Integer id);
    void crear(NivelPermisoCreateRequest c);
    void actualizar(Integer id, NivelPermisoUpdateRequest mt);
    void eliminar(NivelPermisoDeleteRequest e);
}
