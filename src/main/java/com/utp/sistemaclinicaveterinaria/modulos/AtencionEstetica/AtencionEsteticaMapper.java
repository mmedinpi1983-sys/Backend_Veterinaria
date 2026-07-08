package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.AtencionEsteticaDTO.AtencionEsteticaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection.AtencionEsteticaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection.AtencionEsteticaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AtencionEsteticaMapper {

    AtencionEsteticaListResponse AtencionEsteticaListMapper(AtencionEsteticaListarProjection projection);

    List<AtencionEsteticaListResponse> AtencionEsteticaListMapperList(List<AtencionEsteticaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    AtencionEsteticaDetailResponse AtencionEsteticaDetailMapper(AtencionEsteticaDetalleProjection projection);

    @Mapping(target = "idEstetica", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    AtencionEstetica toEntity(AtencionEsteticaCreateRequest dto);

    @Mapping(target = "idEstetica", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    void updateEntity(@MappingTarget AtencionEstetica entity, AtencionEsteticaUpdateRequest dto);
}
