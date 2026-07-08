package com.utp.sistemaclinicaveterinaria.modulos.Asociado;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.AsociadoDTO.AsociadoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection.AsociadoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection.AsociadoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AsociadoMapper {

    AsociadoListResponse AsociadoListMapper(AsociadoListarProjection projection);

    List<AsociadoListResponse> AsociadoListMapperList(List<AsociadoListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "superAdminCreador")
    @Mapping(target = "usuarioModificador", source = "superAdminModificador")
    @Mapping(target = "usuarioEliminador", source = "superAdminEliminador")
    AsociadoDetailResponse AsociadoDetailMapper(AsociadoDetalleProjection projection);

    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    Asociado toEntity(AsociadoCreateRequest dto);

    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idSuperAdminCreador", ignore = true)
    @Mapping(target = "idSuperAdminModificador", ignore = true)
    @Mapping(target = "idSuperAdminEliminador", ignore = true)
    void updateEntity(@MappingTarget Asociado entity, AsociadoUpdateRequest dto);
}
