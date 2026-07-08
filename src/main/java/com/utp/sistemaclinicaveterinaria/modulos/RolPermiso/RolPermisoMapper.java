package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.RolPermisoDTO.RolPermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RolPermisoMapper {

    RolPermisoCatalogResponse RolPermisoCatalogMapper(RolPermisoCatalogoProjection projection);

    List<RolPermisoCatalogResponse> RolPermisoCatalogoMapperList(List<RolPermisoCatalogoProjection> projections);

    RolPermisoListResponse RolPermisoListMapper(RolPermisoListarProjection projection);

    List<RolPermisoListResponse> RolPermisoListMapperList(List<RolPermisoListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    RolPermisoDetailResponse RolPermisoDetailMapper(RolPermisoDetalleProjection projection);

    @Mapping(target = "idRolPermiso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    RolPermiso toEntity(RolPermisoCreateRequest dto);

    @Mapping(target = "idRolPermiso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget RolPermiso entity, RolPermisoUpdateRequest dto);
}
