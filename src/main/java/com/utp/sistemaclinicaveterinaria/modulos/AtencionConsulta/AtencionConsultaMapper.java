package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.AtencionConsultaDTO.AtencionConsultaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection.AtencionConsultaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection.AtencionConsultaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AtencionConsultaMapper {

    AtencionConsultaListResponse AtencionConsultaListMapper(AtencionConsultaListarProjection projection);

    List<AtencionConsultaListResponse> AtencionConsultaListMapperList(List<AtencionConsultaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    AtencionConsultaDetailResponse AtencionConsultaDetailMapper(AtencionConsultaDetalleProjection projection);

    @Mapping(target = "idConsulta", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    AtencionConsulta toEntity(AtencionConsultaCreateRequest dto);

    @Mapping(target = "idConsulta", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget AtencionConsulta entity, AtencionConsultaUpdateRequest dto);
}
