package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeUpdateRequest;

public interface TriajeService {
    List<TriajeCatalogResponse> catalogo(Integer idAsociado);
    List<TriajeListResponse> listar(TriajeFilterRequest f);
    TriajeDetailResponse obtenerId(Integer idTriaje, Integer idAsociado);
    void crear(TriajeCreateRequest c);
    void actualizar(Integer idTriaje, TriajeUpdateRequest mt);
    void eliminar(TriajeDeleteRequest e);
}
