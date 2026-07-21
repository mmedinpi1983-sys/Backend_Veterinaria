package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.ConsultorioDTO.ConsultorioUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultorioMapper {

    ConsultorioCatalogResponse toCatalogResponse(ConsultorioCatalogoProjection projection);

    List<ConsultorioCatalogResponse> toCatalogResponseList(List<ConsultorioCatalogoProjection> projections);

    ConsultorioListResponse toListResponse(ConsultorioListarProjection projection);

    List<ConsultorioListResponse> toListResponseList(List<ConsultorioListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    ConsultorioDetailResponse toDetailResponse(ConsultorioDetalleProjection projection);

    @Mapping(target = "idConsultorio", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Consultorio toEntity(ConsultorioCreateRequest dto);

    @Mapping(target = "idConsultorio", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Consultorio entity, ConsultorioUpdateRequest dto);
}
