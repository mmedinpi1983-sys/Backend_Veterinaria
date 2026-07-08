package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.EstadoProgramacionDTO.EstadoProgramacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EstadoProgramacionMapper {

    @Mapping(target = "nombre", source = "nombre")
    EstadoProgramacionCatalogResponse EstadoProgramacionCatalogMapper(EstadoProgramacionCatalogoProjection projection);

    List<EstadoProgramacionCatalogResponse> EstadoProgramacionCatalogoMapperList(List<EstadoProgramacionCatalogoProjection> projections);

    EstadoProgramacionListResponse EstadoProgramacionListMapper(EstadoProgramacionListarProjection projection);

    List<EstadoProgramacionListResponse> EstadoProgramacionListMapperList(List<EstadoProgramacionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    EstadoProgramacionDetailResponse EstadoProgramacionDetailMapper(EstadoProgramacionDetalleProjection projection);

    @Mapping(target = "idEstadoProgramacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    EstadoProgramacion toEntity(EstadoProgramacionCreateRequest dto);

    @Mapping(target = "idEstadoProgramacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget EstadoProgramacion entity, EstadoProgramacionUpdateRequest dto);
}
