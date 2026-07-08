package com.utp.sistemaclinicaveterinaria.modulos.Asociado;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoUpdateRequest;

public interface AsociadoService {
    List<AsociadoListResponse> listar();
    AsociadoDetailResponse obtenerId(Integer idAsociado);
    void crear(AsociadoCreateRequest c);
    void actualizar(Integer idAsociado, AsociadoUpdateRequest mt);
    void eliminar(AsociadoDeleteRequest e);
}
