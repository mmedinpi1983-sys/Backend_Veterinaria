package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoUpdateRequest;

public interface PermisoService {
    List<PermisoCatalogResponse> catalogo(Integer idAsociado);
    List<PermisoListResponse> listar(PermisoFilterRequest f);
    PermisoDetailResponse obtenerId(Integer idPermiso, Integer idAsociado);
    void crear(PermisoCreateRequest c);
    void actualizar(Integer idPermiso, PermisoUpdateRequest mt);
    void eliminar(PermisoDeleteRequest e);
}
