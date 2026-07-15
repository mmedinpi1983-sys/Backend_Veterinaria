package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiagnosticoCatalogoMapper {

    DiagnosticoCatalogoCatalogResponse DiagnosticoCatalogoCatalogMapper(DiagnosticoCatalogoCatalogoProjection projection);

    List<DiagnosticoCatalogoCatalogResponse> DiagnosticoCatalogoCatalogoMapperList(List<DiagnosticoCatalogoCatalogoProjection> projections);

    DiagnosticoCatalogoListResponse DiagnosticoCatalogoListMapper(DiagnosticoCatalogoListarProjection projection);

    List<DiagnosticoCatalogoListResponse> DiagnosticoCatalogoListMapperList(List<DiagnosticoCatalogoListarProjection> projections);

    DiagnosticoCatalogoDetailResponse DiagnosticoCatalogoDetailMapper(DiagnosticoCatalogoDetalleProjection projection);

    @Mapping(target = "idDiagnosticoCatalogo", ignore = true)
    DiagnosticoCatalogo toEntity(DiagnosticoCatalogoCreateRequest dto);

    @Mapping(target = "idDiagnosticoCatalogo", ignore = true)
    void updateEntity(@MappingTarget DiagnosticoCatalogo entity, DiagnosticoCatalogoUpdateRequest dto);
}
