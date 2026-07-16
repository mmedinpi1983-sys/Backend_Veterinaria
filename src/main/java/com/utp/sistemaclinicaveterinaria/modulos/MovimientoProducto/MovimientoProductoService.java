package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.ClaseResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MotivoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoStatsResponse;

public interface MovimientoProductoService {
    List<MovimientoListResponse> listar(MovimientoFilterRequest f);
    MovimientoStatsResponse stats();
    List<ClaseResponse> clases();
    List<MotivoResponse> motivos();
    void crear(MovimientoCreateRequest c);
}
