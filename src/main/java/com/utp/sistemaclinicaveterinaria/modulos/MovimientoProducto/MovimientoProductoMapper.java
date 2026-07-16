package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.ClaseResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MotivoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.ClaseProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MotivoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MovimientoListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MovimientoStatsProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovimientoProductoMapper {
    MovimientoListResponse toList(MovimientoListarProjection p);
    List<MovimientoListResponse> toList(List<MovimientoListarProjection> p);
    MovimientoStatsResponse toStats(MovimientoStatsProjection p);
    ClaseResponse toClase(ClaseProjection p);
    List<ClaseResponse> toClase(List<ClaseProjection> p);
    MotivoResponse toMotivo(MotivoProjection p);
    List<MotivoResponse> toMotivo(List<MotivoProjection> p);
}
