package com.utp.sistemaclinicaveterinaria.modulos.Programacion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionUpdateRequest;

public interface ProgramacionService {
    List<ProgramacionCatalogResponse> catalogo(Integer idAsociado);
    List<ProgramacionListResponse> listar(ProgramacionFilterRequest f);
    ProgramacionDetailResponse obtenerId(Integer id, Integer idAsociado);
    void crear(ProgramacionCreateRequest c);
    void actualizar(Integer id, ProgramacionUpdateRequest t);
    void eliminar(ProgramacionDeleteRequest e);
}
