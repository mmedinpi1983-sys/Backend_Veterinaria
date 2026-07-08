package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleUpdateRequest;

public interface RecetaDetalleService {
    List<RecetaDetalleCatalogResponse> catalogo();
    List<RecetaDetalleListResponse> listar();
    RecetaDetalleDetailResponse obtenerId(Integer id);
    void crear(RecetaDetalleCreateRequest c);
    void actualizar(Integer id, RecetaDetalleUpdateRequest t);
    void eliminar(RecetaDetalleDeleteRequest e);
}
