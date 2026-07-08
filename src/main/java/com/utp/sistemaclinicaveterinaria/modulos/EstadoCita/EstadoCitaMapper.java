package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.EstadoCitaDTO.EstadoCitaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EstadoCitaMapper {

    @Mapping(target = "nombre", source = "nombre")
    EstadoCitaCatalogResponse EstadoCitaCatalogMapper(EstadoCitaCatalogoProjection projection);

    List<EstadoCitaCatalogResponse> EstadoCitaCatalogoMapperList(List<EstadoCitaCatalogoProjection> projections);

    EstadoCitaListResponse EstadoCitaListMapper(EstadoCitaListarProjection projection);

    List<EstadoCitaListResponse> EstadoCitaListMapperList(List<EstadoCitaListarProjection> projections);

    EstadoCitaDetailResponse EstadoCitaDetailMapper(EstadoCitaDetalleProjection projection);

    @Mapping(target = "idEstadoCita", ignore = true)
    EstadoCita toEntity(EstadoCitaCreateRequest dto);

    @Mapping(target = "idEstadoCita", ignore = true)
    void updateEntity(@MappingTarget EstadoCita entity, EstadoCitaUpdateRequest dto);
}
