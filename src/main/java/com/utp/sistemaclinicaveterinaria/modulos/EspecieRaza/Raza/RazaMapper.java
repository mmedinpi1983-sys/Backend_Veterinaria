package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.RazaDTO.RazaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.Projection.RazaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.Projection.RazaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza.Projection.RazaListarProjection;

@Mapper(componentModel = "spring")
public interface RazaMapper {

    // objeto del catalogo
    default RazaCatalogResponse RazaCatalogMapper(RazaCatalogoProjection r) {
        return new RazaCatalogResponse(
                r.getIdEspecieRaza(),
                r.getNombre(),
                r.getIdEspecie());
    }

    // objeto que se mostrara en la lista
    default RazaListResponse RazaListMapper(RazaListarProjection r) {
        return new RazaListResponse(
                r.getIdEspecieRaza(),
                r.getNombre(),
                r.getNombreEspecie(),
                r.getEstado(),
                r.getFechaCreacion());
    }

    // objeto completo que se mostrara
    default RazaDetailResponse RazaDetailMapper(RazaDetalleProjection r) {
        return new RazaDetailResponse(
                r.getIdEspecieRaza(),
                r.getNombre(),
                r.getIdEspecie(),
                r.getNombreEspecie(),
                r.getEstado(),
                r.getEmpleadoCreador(),
                r.getFechaCreacion(),
                r.getEmpleadoModificador(),
                r.getFechaModificacion(),
                r.getEmpleadoEliminador(),
                r.getFechaEliminacion());
    }

    // catalogo lista
    default List<RazaCatalogResponse> catalogoMapper(List<RazaCatalogoProjection> r) {
        return r.stream().map(this::RazaCatalogMapper).toList();
    }

    // filas de tabla
    default List<RazaListResponse> listarMapper(List<RazaListarProjection> r) {
        return r.stream().map(this::RazaListMapper).toList();
    }

    // crear nueva
    @Mapping(target = "idEspecieRaza", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    EspecieRaza toEntity(RazaCreateRequest dto);

    // modificar el objeto
    @Mapping(target = "idEspecieRaza", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget EspecieRaza entity, RazaUpdateRequest dto);
}