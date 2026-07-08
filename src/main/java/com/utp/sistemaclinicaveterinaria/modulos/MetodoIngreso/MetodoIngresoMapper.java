package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetodoIngresoMapper {

    MetodoIngresoCatalogResponse MetodoIngresoCatalogMapper(MetodoIngresoCatalogoProjection projection);

    List<MetodoIngresoCatalogResponse> MetodoIngresoCatalogoMapperList(List<MetodoIngresoCatalogoProjection> projections);

    MetodoIngresoListResponse MetodoIngresoListMapper(MetodoIngresoListarProjection projection);

    List<MetodoIngresoListResponse> MetodoIngresoListMapperList(List<MetodoIngresoListarProjection> projections);

    MetodoIngresoDetailResponse MetodoIngresoDetailMapper(MetodoIngresoDetalleProjection projection);

    @Mapping(target = "idMetodoIngreso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    MetodoIngreso toEntity(MetodoIngresoCreateRequest dto);

    @Mapping(target = "idMetodoIngreso", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void updateEntity(@MappingTarget MetodoIngreso entity, MetodoIngresoUpdateRequest dto);
}
