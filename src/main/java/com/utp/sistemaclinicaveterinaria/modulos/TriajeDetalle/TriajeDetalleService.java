package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleUpdateRequest;

public interface TriajeDetalleService {
    List<TriajeDetalleCatalogResponse> catalogo();
    List<TriajeDetalleListResponse> listar(TriajeDetalleFilterRequest f);
    TriajeDetalleDetailResponse obtenerId(Integer idTriajeDetalle);
    void crear(TriajeDetalleCreateRequest c);
    void actualizar(Integer idTriajeDetalle, TriajeDetalleUpdateRequest mt);
    void eliminar(TriajeDetalleDeleteRequest e);
}
