package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.RecetaDetalleDTO.RecetaDetalleUpdateRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecetaDetalleMapper {

    RecetaDetalleCatalogResponse toCatalogResponse(RecetaDetalleCatalogoProjection projection);

    List<RecetaDetalleCatalogResponse> toCatalogResponseList(List<RecetaDetalleCatalogoProjection> projections);

    RecetaDetalleListResponse toListResponse(RecetaDetalleListarProjection projection);

    List<RecetaDetalleListResponse> toListResponseList(List<RecetaDetalleListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    RecetaDetalleDetailResponse toDetailResponse(RecetaDetalleDetalleProjection projection);

    @Mapping(target = "idRecetaDetalle", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    RecetaDetalle toEntity(RecetaDetalleCreateRequest dto);

    @Mapping(target = "idRecetaDetalle", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget RecetaDetalle entity, RecetaDetalleUpdateRequest dto);
}
