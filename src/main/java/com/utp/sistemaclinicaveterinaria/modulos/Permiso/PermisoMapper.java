package com.utp.sistemaclinicaveterinaria.modulos.Permiso;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.PermisoDTO.PermisoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermisoMapper {

    @Mapping(target = "nombrePermiso", source = "nombrePermiso")
    PermisoCatalogResponse PermisoCatalogMapper(PermisoCatalogoProjection projection);

    List<PermisoCatalogResponse> PermisoCatalogoMapperList(List<PermisoCatalogoProjection> projections);

    PermisoListResponse PermisoListMapper(PermisoListarProjection projection);

    List<PermisoListResponse> PermisoListMapperList(List<PermisoListarProjection> projections);

    PermisoDetailResponse PermisoDetailMapper(PermisoDetalleProjection projections);

    @Mapping(target = "idPermiso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    Permiso toEntity(PermisoCreateRequest dto);

    @Mapping(target = "idPermiso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    void updateEntity(@MappingTarget Permiso entity, PermisoUpdateRequest dto);
}
