package com.utp.sistemaclinicaveterinaria.modulos.Mascota;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.MascotaDTO.MascotaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MascotaMapper {

    MascotaCatalogResponse MascotaCatalogoMapper(MascotaCatalogoProjection projection);

    List<MascotaCatalogResponse> MascotaCatalogoMapperList(List<MascotaCatalogoProjection> projections);

    MascotaListResponse MascotaListMapper(MascotaListarProjection projection);

    List<MascotaListResponse> MascotaListMapperList(List<MascotaListarProjection> projections);

    @Mapping(target = "empleadoCreador", source = "empleadoCreador")
    @Mapping(target = "empleadoModificador", source = "empleadoModificador")
    @Mapping(target = "empleadoEliminador", source = "empleadoEliminador")
    MascotaDetailResponse MascotaDetailMapper(MascotaDetalleProjection projection);

    @Mapping(target = "idMascota", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Mascota toEntity(MascotaCreateRequest dto);

    @Mapping(target = "idMascota", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Mascota entity, MascotaUpdateRequest dto);
}
