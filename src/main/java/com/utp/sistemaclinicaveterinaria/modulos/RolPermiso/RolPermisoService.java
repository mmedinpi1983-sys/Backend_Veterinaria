package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoUpdateRequest;

public interface RolPermisoService {
    List<RolPermisoCatalogResponse> catalogo(Integer idAsociado);
    List<RolPermisoListResponse> listar(RolPermisoFilterRequest f);
    RolPermisoDetailResponse obtenerId(Integer idRolPermiso, Integer idAsociado);
    void crear(RolPermisoCreateRequest c);
    void actualizar(Integer idRolPermiso, RolPermisoUpdateRequest mt);
    void eliminar(RolPermisoDeleteRequest e);
}
