package com.utp.sistemaclinicaveterinaria.modulos.Turno;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TurnoMapper {
    // catalogo
    @Mapping(target = "turno", source = "nombre")
    TurnoCatalogResponse TurnoCatalogMapper(TurnoCatalogoProjection projection);

    List<TurnoCatalogResponse> TurnoCatalogoMapperList(List<TurnoCatalogoProjection> projections);

    // lista
    TurnoListResponse TurnoListMapper(TurnoListarProjection projection);

    List<TurnoListResponse> TurnoListMapperList(List<TurnoListarProjection> projections);

    // detalle
    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    TurnoDetailResponse TurnoDetailMapper(TurnoDetalleProjection projections);

    // crear un nuevo turno
    @Mapping(target = "idTurno", ignore = true)
    @Mapping(target = "horaFin", source = "horaFin")
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Turno toEntity(TurnoCreateRequest dto);

    // modificar Turno
    @Mapping(target = "idTurno", ignore = true)
    @Mapping(target = "horaFin", source = "horaFin")
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Turno entity, TurnoUpdateRequest dto);

}
