package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.AnamnesisDTO.AnamnesisUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection.AnamnesisDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection.AnamnesisListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnamnesisMapper {

    AnamnesisListResponse AnamnesisListMapper(AnamnesisListarProjection projection);

    List<AnamnesisListResponse> AnamnesisListMapperList(List<AnamnesisListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    AnamnesisDetailResponse AnamnesisDetailMapper(AnamnesisDetalleProjection projection);

    @Mapping(target = "idAnamnesis", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Anamnesis toEntity(AnamnesisCreateRequest dto);

    @Mapping(target = "idAnamnesis", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Anamnesis entity, AnamnesisUpdateRequest dto);
}
