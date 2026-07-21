package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.EspecieDTO.EspecieUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.Projection.EspecieCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.Projection.EspecieDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie.Projection.EspecieListarProjection;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

    // objeto de catalogo
    default EspecieCatalogResponse EspecieCatalogMapper(EspecieCatalogoProjection e) {
        return new EspecieCatalogResponse(
                e.getIdEspecieRaza(),
                e.getNombre());
    }

    // objeto que se mostrara en lista
    default EspecieListResponse EspecieListMapper(EspecieListarProjection e) {
        return new EspecieListResponse(
                e.getIdEspecieRaza(),
                e.getNombre(),
                e.getEstado(),
                e.getFechaCreacion());
    }

    // Objecto completo que se mostrar
    default EspecieDetailResponse EspecieDetailMapper(EspecieDetalleProjection e) {
        return new EspecieDetailResponse(
                e.getIdEspecieRaza(),
                e.getNombre(),
                e.getEstado(),
                e.getEmpleadoCreador(),
                e.getFechaCreacion(),
                e.getEmpleadoModificador(),
                e.getFechaModificacion(),
                e.getEmpleadoEliminador(),
                e.getFechaEliminacion());
    }

    // catalogo lista
    default List<EspecieCatalogResponse> catalogoMapper(List<EspecieCatalogoProjection> e) {
        return e.stream().map(this::EspecieCatalogMapper).toList();
    }

    // filas de tabla
    default List<EspecieListResponse> listarMapper(List<EspecieListarProjection> e) {
        return e.stream().map(this::EspecieListMapper).toList();
    }

    // crear nueva especie
    @Mapping(target = "idEspecieRaza", ignore = true)
    @Mapping(target = "idEspecie", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    EspecieRaza toEntity(EspecieCreateRequest dto);

    // modificar el objeto
    @Mapping(target = "idEspecieRaza", ignore = true)
    @Mapping(target = "idEspecie", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget EspecieRaza entity, EspecieUpdateRequest dto);

}