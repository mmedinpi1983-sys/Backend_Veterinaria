package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoUpdateRequest;
public interface DuenoService {
    List<DuenoCatalogResponse> catalogo(Integer idAsociado);
    List<DuenoListResponse> listar(DuenoFilterRequest f);
    DuenoDetailResponse obtenerId(Integer idDueno, Integer idAsociado);
    void crear(DuenoCreateRequest c);
    void actualizar(Integer idDueno, DuenoUpdateRequest u);
    void eliminar(DuenoDeleteRequest e);
}
