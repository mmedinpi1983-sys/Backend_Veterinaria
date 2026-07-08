package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.AtencionVacunacionDTO.AtencionVacunacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection.AtencionVacunacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection.AtencionVacunacionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AtencionVacunacionMapper {

    AtencionVacunacionListResponse AtencionVacunacionListMapper(AtencionVacunacionListarProjection projection);

    List<AtencionVacunacionListResponse> AtencionVacunacionListMapperList(List<AtencionVacunacionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    AtencionVacunacionDetailResponse AtencionVacunacionDetailMapper(AtencionVacunacionDetalleProjection projection);

    @Mapping(target = "idVacunacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    AtencionVacunacion toEntity(AtencionVacunacionCreateRequest dto);

    @Mapping(target = "idVacunacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget AtencionVacunacion entity, AtencionVacunacionUpdateRequest dto);
}
