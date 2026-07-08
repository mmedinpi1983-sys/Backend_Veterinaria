package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoUpdateRequest;

public interface MedicamentoCatalogoService {
    List<MedicamentoCatalogoCatalogResponse> catalogo();
    List<MedicamentoCatalogoListResponse> listar();
    MedicamentoCatalogoDetailResponse obtenerId(Integer id);
    void crear(MedicamentoCatalogoCreateRequest c);
    void actualizar(Integer id, MedicamentoCatalogoUpdateRequest mt);
    void eliminar(MedicamentoCatalogoDeleteRequest e);
}
