package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaUpdateRequest;

public interface RazaService {
    List<RazaCatalogResponse> catalogo(Integer idAsociado);

    List<RazaListResponse> listar(RazaFilterRequest f);

    RazaDetailResponse obtenerId(Integer idRaza, Integer idAsociado);

    void crear(RazaCreateRequest c);

    void actualizar(Integer idRaza, RazaUpdateRequest m);

    void eliminar(RazaDeleteRequest e);

}