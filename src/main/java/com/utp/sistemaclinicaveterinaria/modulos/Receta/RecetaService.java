package com.utp.sistemaclinicaveterinaria.modulos.Receta;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaUpdateRequest;

public interface RecetaService {
    List<RecetaCatalogResponse> catalogo(Integer idAsociado);
    List<RecetaListResponse> listar();
    RecetaDetailResponse obtenerId(Integer id, Integer idAsociado);
    Integer crear(RecetaCreateRequest c);
    void actualizar(Integer id, RecetaUpdateRequest t);
    void eliminar(RecetaDeleteRequest e);
}
