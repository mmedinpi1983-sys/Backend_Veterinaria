package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaUpdateRequest;

public interface RolesClinicaService {
    List<RolesClinicaCatalogResponse> catalogo(Integer idAsociado);
    List<RolesClinicaListResponse> listar(RolesClinicaFilterRequest f);
    RolesClinicaDetailResponse obtenerId(Integer idRoles, Integer idAsociado);
    void crear(RolesClinicaCreateRequest c);
    void actualizar(Integer idRoles, RolesClinicaUpdateRequest mt);
    void eliminar(RolesClinicaDeleteRequest e);
}
