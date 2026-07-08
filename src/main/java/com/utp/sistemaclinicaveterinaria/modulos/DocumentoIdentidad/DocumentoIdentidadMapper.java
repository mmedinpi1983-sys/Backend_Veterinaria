package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.DocumentoIdentidadDTO.DocumentoIdentidadUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentoIdentidadMapper {

    DocumentoIdentidadCatalogResponse DocumentoIdentidadCatalogMapper(DocumentoIdentidadCatalogoProjection projection);

    List<DocumentoIdentidadCatalogResponse> DocumentoIdentidadCatalogoMapperList(List<DocumentoIdentidadCatalogoProjection> projections);

    DocumentoIdentidadListResponse DocumentoIdentidadListMapper(DocumentoIdentidadListarProjection projection);

    List<DocumentoIdentidadListResponse> DocumentoIdentidadListMapperList(List<DocumentoIdentidadListarProjection> projections);

    @Mapping(target = "descripcion", source = "descripcion")
    DocumentoIdentidadDetailResponse DocumentoIdentidadDetailMapper(DocumentoIdentidadDetalleProjection projection);

    @Mapping(target = "idDocumentoIdentidad", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    DocumentoIdentidad toEntity(DocumentoIdentidadCreateRequest dto);

    @Mapping(target = "idDocumentoIdentidad", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    void updateEntity(@MappingTarget DocumentoIdentidad entity, DocumentoIdentidadUpdateRequest dto);
}
