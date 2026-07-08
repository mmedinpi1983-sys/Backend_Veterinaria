package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisUpdateRequest;

public interface AnamnesisService {
    List<AnamnesisListResponse> listar();
    AnamnesisDetailResponse obtenerId(Integer idAnamnesis);
    void crear(AnamnesisCreateRequest c);
    void actualizar(Integer idAnamnesis, AnamnesisUpdateRequest mt);
    void eliminar(AnamnesisDeleteRequest e);
}
