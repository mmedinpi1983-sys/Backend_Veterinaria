package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.EstadoAtencionDTO.EstadoAtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EstadoAtencionMapper {

    @Mapping(target = "nombre", source = "nombre")
    EstadoAtencionCatalogResponse EstadoAtencionCatalogMapper(EstadoAtencionCatalogoProjection projection);

    List<EstadoAtencionCatalogResponse> EstadoAtencionCatalogoMapperList(List<EstadoAtencionCatalogoProjection> projections);

    EstadoAtencionListResponse EstadoAtencionListMapper(EstadoAtencionListarProjection projection);

    List<EstadoAtencionListResponse> EstadoAtencionListMapperList(List<EstadoAtencionListarProjection> projections);

    EstadoAtencionDetailResponse EstadoAtencionDetailMapper(EstadoAtencionDetalleProjection projection);

    @Mapping(target = "idEstadoAtencion", ignore = true)
    EstadoAtencion toEntity(EstadoAtencionCreateRequest dto);

    @Mapping(target = "idEstadoAtencion", ignore = true)
    void updateEntity(@MappingTarget EstadoAtencion entity, EstadoAtencionUpdateRequest dto);
}
