package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.DuenoMascotaDTO.DuenoMascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DuenoMascotaMapper {

    DuenoMascotaCatalogResponse DuenoMascotaCatalogoMapper(DuenoMascotaCatalogoProjection projection);

    List<DuenoMascotaCatalogResponse> DuenoMascotaCatalogoMapperList(List<DuenoMascotaCatalogoProjection> projections);

    DuenoMascotaListResponse DuenoMascotaListMapper(DuenoMascotaListarProjection projection);

    List<DuenoMascotaListResponse> DuenoMascotaListMapperList(List<DuenoMascotaListarProjection> projections);

    @Mapping(target = "empleadoCreador", source = "empleadoCreador")
    @Mapping(target = "empleadoModificador", source = "empleadoModificador")
    @Mapping(target = "empleadoEliminador", source = "empleadoEliminador")
    DuenoMascotaDetailResponse DuenoMascotaDetailMapper(DuenoMascotaDetalleProjection projection);

    @Mapping(target = "idDuenoMascota", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    DuenoMascota toEntity(DuenoMascotaCreateRequest dto);

    @Mapping(target = "idDuenoMascota", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget DuenoMascota entity, DuenoMascotaUpdateRequest dto);
}