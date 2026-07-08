package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaUpdateRequest;

public interface AtencionConsultaService {
    List<AtencionConsultaListResponse> listar();
    AtencionConsultaDetailResponse obtenerId(Integer idConsulta);
    void crear(AtencionConsultaCreateRequest c);
    void actualizar(Integer idConsulta, AtencionConsultaUpdateRequest mt);
    void eliminar(AtencionConsultaDeleteRequest e);
}
