package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionUpdateRequest;

public interface NivelSuscripcionService {
    List<NivelSuscripcionCatalogResponse> catalogo();
    List<NivelSuscripcionListResponse> listar();
    NivelSuscripcionDetailResponse obtenerId(Integer id);
    void crear(NivelSuscripcionCreateRequest c);
    void actualizar(Integer id, NivelSuscripcionUpdateRequest mt);
    void eliminar(NivelSuscripcionDeleteRequest e);
}
