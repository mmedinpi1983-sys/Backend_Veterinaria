package com.utp.sistemaclinicaveterinaria.modulos.Triaje;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.TriajeDTO.TriajeUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TriajeMapper {

    TriajeCatalogResponse TriajeCatalogMapper(TriajeCatalogoProjection projection);

    List<TriajeCatalogResponse> TriajeCatalogoMapperList(List<TriajeCatalogoProjection> projections);

    TriajeListResponse TriajeListMapper(TriajeListarProjection projection);

    List<TriajeListResponse> TriajeListMapperList(List<TriajeListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    TriajeDetailResponse TriajeDetailMapper(TriajeDetalleProjection projection);

    @Mapping(target = "idTriaje", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Triaje toEntity(TriajeCreateRequest dto);

    @Mapping(target = "idTriaje", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Triaje entity, TriajeUpdateRequest dto);
}
