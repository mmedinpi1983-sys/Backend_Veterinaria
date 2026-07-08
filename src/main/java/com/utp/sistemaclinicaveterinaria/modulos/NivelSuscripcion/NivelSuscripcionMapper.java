package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.NivelSuscripcionDTO.NivelSuscripcionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NivelSuscripcionMapper {

    NivelSuscripcionCatalogResponse NivelSuscripcionCatalogMapper(NivelSuscripcionCatalogoProjection projection);

    List<NivelSuscripcionCatalogResponse> NivelSuscripcionCatalogoMapperList(List<NivelSuscripcionCatalogoProjection> projections);

    NivelSuscripcionListResponse NivelSuscripcionListMapper(NivelSuscripcionListarProjection projection);

    List<NivelSuscripcionListResponse> NivelSuscripcionListMapperList(List<NivelSuscripcionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    NivelSuscripcionDetailResponse NivelSuscripcionDetailMapper(NivelSuscripcionDetalleProjection projection);

    @Mapping(target = "idNivel", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    NivelSuscripcion toEntity(NivelSuscripcionCreateRequest dto);

    @Mapping(target = "idNivel", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    void updateEntity(@MappingTarget NivelSuscripcion entity, NivelSuscripcionUpdateRequest dto);
}
