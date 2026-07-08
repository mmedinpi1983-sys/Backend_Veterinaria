package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.EmpleadoAsociadoDTO.EmpleadoAsociadoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmpleadoAsociadoMapper {

    EmpleadoAsociadoCatalogResponse EmpleadoAsociadoCatalogoMapper(EmpleadoAsociadoCatalogoProjection projection);

    List<EmpleadoAsociadoCatalogResponse> EmpleadoAsociadoCatalogoMapperList(List<EmpleadoAsociadoCatalogoProjection> projections);

    EmpleadoAsociadoListResponse EmpleadoAsociadoListMapper(EmpleadoAsociadoListarProjection projection);

    List<EmpleadoAsociadoListResponse> EmpleadoAsociadoListMapperList(List<EmpleadoAsociadoListarProjection> projections);

    @Mapping(target = "empleadoCreador", source = "empleadoCreador")
    @Mapping(target = "empleadoModificador", source = "empleadoModificador")
    @Mapping(target = "empleadoEliminador", source = "empleadoEliminador")
    EmpleadoAsociadoDetailResponse EmpleadoAsociadoDetailMapper(EmpleadoAsociadoDetalleProjection projection);

    @Mapping(target = "idEmpleadoAsociado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "contrasena", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    EmpleadoAsociado toEntity(EmpleadoAsociadoCreateRequest dto);

    @Mapping(target = "idEmpleadoAsociado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget EmpleadoAsociado entity, EmpleadoAsociadoUpdateRequest dto);
}