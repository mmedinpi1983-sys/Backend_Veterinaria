package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.MedicamentoCatalogoDTO.MedicamentoCatalogoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicamentoCatalogoMapper {

    MedicamentoCatalogoCatalogResponse MedicamentoCatalogoCatalogMapper(MedicamentoCatalogoCatalogoProjection projection);

    List<MedicamentoCatalogoCatalogResponse> MedicamentoCatalogoCatalogoMapperList(List<MedicamentoCatalogoCatalogoProjection> projections);

    MedicamentoCatalogoListResponse MedicamentoCatalogoListMapper(MedicamentoCatalogoListarProjection projection);

    List<MedicamentoCatalogoListResponse> MedicamentoCatalogoListMapperList(List<MedicamentoCatalogoListarProjection> projections);

    MedicamentoCatalogoDetailResponse MedicamentoCatalogoDetailMapper(MedicamentoCatalogoDetalleProjection projection);

    @Mapping(target = "idMedicamento", ignore = true)
    MedicamentoCatalogo toEntity(MedicamentoCatalogoCreateRequest dto);

    @Mapping(target = "idMedicamento", ignore = true)
    void updateEntity(@MappingTarget MedicamentoCatalogo entity, MedicamentoCatalogoUpdateRequest dto);
}
