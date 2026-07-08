package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.TriajeDetalleDTO.TriajeDetalleUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TriajeDetalleMapper {

    TriajeDetalleCatalogResponse TriajeDetalleCatalogMapper(TriajeDetalleCatalogoProjection projection);

    List<TriajeDetalleCatalogResponse> TriajeDetalleCatalogoMapperList(List<TriajeDetalleCatalogoProjection> projections);

    TriajeDetalleListResponse TriajeDetalleListMapper(TriajeDetalleListarProjection projection);

    List<TriajeDetalleListResponse> TriajeDetalleListMapperList(List<TriajeDetalleListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    TriajeDetalleDetailResponse TriajeDetalleDetailMapper(TriajeDetalleDetalleProjection projection);

    @Mapping(target = "idTriajeDetalle", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    TriajeDetalle toEntity(TriajeDetalleCreateRequest dto);

    @Mapping(target = "idTriajeDetalle", ignore = true)
    @Mapping(target = "idTriaje", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget TriajeDetalle entity, TriajeDetalleUpdateRequest dto);
}
