package com.utp.sistemaclinicaveterinaria.modulos.Servicio;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.ServicioDTO.ServicioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServicioMapper {

    @Mapping(target = "nombre", source = "nombre")
    ServicioCatalogResponse ServicioCatalogMapper(ServicioCatalogoProjection projection);

    List<ServicioCatalogResponse> ServicioCatalogoMapperList(List<ServicioCatalogoProjection> projections);

    ServicioListResponse ServicioListMapper(ServicioListarProjection projection);

    List<ServicioListResponse> ServicioListMapperList(List<ServicioListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    ServicioDetailResponse ServicioDetailMapper(ServicioDetalleProjection projections);

    @Mapping(target = "idServicio", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Servicio toEntity(ServicioCreateRequest dto);

    @Mapping(target = "idServicio", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Servicio entity, ServicioUpdateRequest dto);
}
