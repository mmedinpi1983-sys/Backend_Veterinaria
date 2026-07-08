package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.CitaProgramadaDTO.CitaProgramadaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection.CitaProgramadaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection.CitaProgramadaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CitaProgramadaMapper {

    CitaProgramadaListResponse CitaProgramadaListMapper(CitaProgramadaListarProjection projection);

    List<CitaProgramadaListResponse> CitaProgramadaListMapperList(List<CitaProgramadaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    CitaProgramadaDetailResponse CitaProgramadaDetailMapper(CitaProgramadaDetalleProjection projection);

    @Mapping(target = "idCitaProgramada", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    CitaProgramada toEntity(CitaProgramadaCreateRequest dto);

    @Mapping(target = "idCitaProgramada", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    void updateEntity(@MappingTarget CitaProgramada entity, CitaProgramadaUpdateRequest dto);
}
