package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioUpdateRequest;

public interface ConsultorioService {
    List<ConsultorioCatalogResponse> catalogo(Integer idAsociado);
    List<ConsultorioListResponse> listar(ConsultorioFilterRequest f);
    ConsultorioDetailResponse obtenerId(Integer idConsultorio, Integer idAsociado);
    void crear(ConsultorioCreateRequest c);
    void actualizar(Integer idConsultorio, ConsultorioUpdateRequest mt);
    void eliminar(ConsultorioDeleteRequest e);
}
