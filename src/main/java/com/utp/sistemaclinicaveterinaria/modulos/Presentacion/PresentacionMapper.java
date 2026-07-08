package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.PresentacionDTO.PresentacionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PresentacionMapper {

    @Mapping(target = "nombre", source = "nombre")
    PresentacionCatalogResponse PresentacionCatalogMapper(PresentacionCatalogoProjection projection);

    List<PresentacionCatalogResponse> PresentacionCatalogoMapperList(List<PresentacionCatalogoProjection> projections);

    PresentacionListResponse PresentacionListMapper(PresentacionListarProjection projection);

    List<PresentacionListResponse> PresentacionListMapperList(List<PresentacionListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    PresentacionDetailResponse PresentacionDetailMapper(PresentacionDetalleProjection projections);

    @Mapping(target = "idPresentacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Presentacion toEntity(PresentacionCreateRequest dto);

    @Mapping(target = "idPresentacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Presentacion entity, PresentacionUpdateRequest dto);
}
