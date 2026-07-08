package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoUpdateRequest;
public interface EmpleadoAsociadoService {
    List<EmpleadoAsociadoCatalogResponse> catalogo(Integer idAsociado);
    List<EmpleadoAsociadoListResponse> listar(EmpleadoAsociadoFilterRequest f);
    EmpleadoAsociadoDetailResponse obtenerId(Integer idEmpleadoAsociado, Integer idAsociado);
    void crear(EmpleadoAsociadoCreateRequest c);
    void actualizar(Integer idEmpleadoAsociado, EmpleadoAsociadoUpdateRequest u);
    void eliminar(EmpleadoAsociadoDeleteRequest e);
}
