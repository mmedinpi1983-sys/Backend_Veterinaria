package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.EstadoSalidaAtencionDTO.EstadoSalidaAtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EstadoSalidaAtencionMapper {

    @Mapping(target = "nombre", source = "nombre")
    EstadoSalidaAtencionCatalogResponse EstadoSalidaAtencionCatalogMapper(EstadoSalidaAtencionCatalogoProjection projection);

    List<EstadoSalidaAtencionCatalogResponse> EstadoSalidaAtencionCatalogoMapperList(List<EstadoSalidaAtencionCatalogoProjection> projections);

    EstadoSalidaAtencionListResponse EstadoSalidaAtencionListMapper(EstadoSalidaAtencionListarProjection projection);

    List<EstadoSalidaAtencionListResponse> EstadoSalidaAtencionListMapperList(List<EstadoSalidaAtencionListarProjection> projections);

    EstadoSalidaAtencionDetailResponse EstadoSalidaAtencionDetailMapper(EstadoSalidaAtencionDetalleProjection projection);

    @Mapping(target = "idEstadoSalida", ignore = true)
    EstadoSalidaAtencion toEntity(EstadoSalidaAtencionCreateRequest dto);

    @Mapping(target = "idEstadoSalida", ignore = true)
    void updateEntity(@MappingTarget EstadoSalidaAtencion entity, EstadoSalidaAtencionUpdateRequest dto);
}
