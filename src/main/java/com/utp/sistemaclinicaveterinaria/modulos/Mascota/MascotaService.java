package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.SearchItem;
public interface MascotaService {
    List<MascotaCatalogResponse> catalogo(Integer idAsociado);
    List<MascotaListResponse> listar(MascotaFilterRequest f);
    MascotaDetailResponse obtenerId(Integer idMascota, Integer idAsociado);
    Integer crear(MascotaCreateRequest c);
    void actualizar(Integer idMascota, MascotaUpdateRequest u);
    void eliminar(MascotaDeleteRequest e);
    List<SearchItem> buscar(String q);
}
