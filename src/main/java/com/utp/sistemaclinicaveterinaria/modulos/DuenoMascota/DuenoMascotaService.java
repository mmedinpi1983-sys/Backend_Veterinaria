package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaUpdateRequest;
public interface DuenoMascotaService {
    List<DuenoMascotaCatalogResponse> catalogo(Integer idAsociado);
    List<DuenoMascotaListResponse> listar(Integer idAsociado);
    DuenoMascotaDetailResponse obtenerId(Integer idDuenoMascota, Integer idAsociado);
    void crear(DuenoMascotaCreateRequest c);
    void actualizar(Integer idDuenoMascota, DuenoMascotaUpdateRequest u);
    void eliminar(DuenoMascotaDeleteRequest e);
}
