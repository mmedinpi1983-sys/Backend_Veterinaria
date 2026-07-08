package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection.AtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection.AtencionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AtencionMapper {

    AtencionListResponse AtencionListMapper(AtencionListarProjection projection);

    List<AtencionListResponse> AtencionListMapperList(List<AtencionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    AtencionDetailResponse AtencionDetailMapper(AtencionDetalleProjection projection);

    @Mapping(target = "idAtencion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    Atencion toEntity(AtencionCreateRequest dto);

    @Mapping(target = "idAtencion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    void updateEntity(@MappingTarget Atencion entity, AtencionUpdateRequest dto);
}
