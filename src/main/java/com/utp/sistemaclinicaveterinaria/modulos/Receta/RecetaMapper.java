package com.utp.sistemaclinicaveterinaria.modulos.Receta;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.RecetaDTO.RecetaUpdateRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecetaMapper {

    RecetaCatalogResponse toCatalogResponse(RecetaCatalogoProjection projection);

    List<RecetaCatalogResponse> toCatalogResponseList(List<RecetaCatalogoProjection> projections);

    RecetaListResponse toListResponse(RecetaListarProjection projection);

    List<RecetaListResponse> toListResponseList(List<RecetaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    RecetaDetailResponse toDetailResponse(RecetaDetalleProjection projection);

    @Mapping(target = "idReceta", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Receta toEntity(RecetaCreateRequest dto);

    @Mapping(target = "idReceta", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Receta entity, RecetaUpdateRequest dto);
}
