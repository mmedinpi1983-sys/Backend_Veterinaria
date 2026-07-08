package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.NivelPermisoDTO.NivelPermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NivelPermisoMapper {

    NivelPermisoCatalogResponse NivelPermisoCatalogMapper(NivelPermisoCatalogoProjection projection);

    List<NivelPermisoCatalogResponse> NivelPermisoCatalogoMapperList(List<NivelPermisoCatalogoProjection> projections);

    NivelPermisoListResponse NivelPermisoListMapper(NivelPermisoListarProjection projection);

    List<NivelPermisoListResponse> NivelPermisoListMapperList(List<NivelPermisoListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    NivelPermisoDetailResponse NivelPermisoDetailMapper(NivelPermisoDetalleProjection projection);

    @Mapping(target = "idNivelPermiso", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    NivelPermiso toEntity(NivelPermisoCreateRequest dto);

    @Mapping(target = "idNivelPermiso", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    void updateEntity(@MappingTarget NivelPermiso entity, NivelPermisoUpdateRequest dto);
}
