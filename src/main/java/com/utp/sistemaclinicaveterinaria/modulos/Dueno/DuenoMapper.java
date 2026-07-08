package com.utp.sistemaclinicaveterinaria.modulos.Dueno;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.DuenoDTO.DuenoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DuenoMapper {

    DuenoCatalogResponse DuenoCatalogoMapper(DuenoCatalogoProjection projection);

    List<DuenoCatalogResponse> DuenoCatalogoMapperList(List<DuenoCatalogoProjection> projections);

    DuenoListResponse DuenoListMapper(DuenoListarProjection projection);

    List<DuenoListResponse> DuenoListMapperList(List<DuenoListarProjection> projections);

    @Mapping(target = "empleadoCreador", source = "empleadoCreador")
    @Mapping(target = "empleadoModificador", source = "empleadoModificador")
    @Mapping(target = "empleadoEliminador", source = "empleadoEliminador")
    DuenoDetailResponse DuenoDetailMapper(DuenoDetalleProjection projection);

    @Mapping(target = "idDueno", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Dueno toEntity(DuenoCreateRequest dto);

    @Mapping(target = "idDueno", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Dueno entity, DuenoUpdateRequest dto);
}