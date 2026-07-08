package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.RolesClinicaDTO.RolesClinicaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RolesClinicaMapper {

    @Mapping(target = "nombreRol", source = "nombreRol")
    RolesClinicaCatalogResponse RolesClinicaCatalogMapper(RolesClinicaCatalogoProjection projection);

    List<RolesClinicaCatalogResponse> RolesClinicaCatalogoMapperList(List<RolesClinicaCatalogoProjection> projections);

    RolesClinicaListResponse RolesClinicaListMapper(RolesClinicaListarProjection projection);

    List<RolesClinicaListResponse> RolesClinicaListMapperList(List<RolesClinicaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    RolesClinicaDetailResponse RolesClinicaDetailMapper(RolesClinicaDetalleProjection projections);

    @Mapping(target = "idRoles", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    RolesClinica toEntity(RolesClinicaCreateRequest dto);

    @Mapping(target = "idRoles", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget RolesClinica entity, RolesClinicaUpdateRequest dto);
}
