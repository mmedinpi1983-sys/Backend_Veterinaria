package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieUpdateRequest;

public interface EspecieService {

    List<EspecieCatalogResponse> catalogo();

    List<EspecieListResponse> listar(EspecieFilterRequest f);

    EspecieDetailResponse obtenerPorId(Integer idEspecie, Integer idAsociado);

    void crear(EspecieCreateRequest c);

    void actualizar(Integer idEspecie, EspecieUpdateRequest m);

    void eliminar(EspecieDeleteRequest e);

}
