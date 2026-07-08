package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.ProgramacionDTO.ProgramacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProgramacionMapper {

    ProgramacionCatalogResponse toCatalogResponse(ProgramacionCatalogoProjection projection);

    List<ProgramacionCatalogResponse> toCatalogResponseList(List<ProgramacionCatalogoProjection> projections);

    ProgramacionListResponse toListResponse(ProgramacionListarProjection projection);

    List<ProgramacionListResponse> toListResponseList(List<ProgramacionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    ProgramacionDetailResponse toDetailResponse(ProgramacionDetalleProjection projection);

    @Mapping(target = "idProgramacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Programacion toEntity(ProgramacionCreateRequest dto);

    @Mapping(target = "idProgramacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Programacion entity, ProgramacionUpdateRequest dto);
}
