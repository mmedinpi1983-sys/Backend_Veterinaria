package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.time.LocalDateTime;
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

@Mapper(componentModel = "spring")
public interface EspecieMapper {

    // objeto de catalogo
    default EspecieCatalogResponse EspecieCatalogMapper(Object[] e) {
        return new EspecieCatalogResponse(
                (Integer) e[0],
                (String) e[1]);
    }

    // objeto que se mostrara en lista
    default EspecieListResponse EspecieListMapper(Object[] e) {
        return new EspecieListResponse(
                (Integer) e[0],
                (String) e[1],
                (Boolean) e[2],
                (LocalDateTime) e[3]);
    }

    // Objecto completo que se mostrar
    default EspecieDetailResponse EspecieDetailMapper(Object[] e) {
        return new EspecieDetailResponse(
                (Integer) e[0],
                (String) e[1],
                (Boolean) e[2],
                (String) e[3],
                (LocalDateTime) e[4],
                (String) e[5],
                (LocalDateTime) e[6],
                (String) e[7],
                (LocalDateTime) e[8]);
    }

    // catalogo lista
    default List<EspecieCatalogResponse> catalogoMapper(List<Object[]> e) {
        return e.stream().map(this::EspecieCatalogMapper).toList();
    }

    // filas de tabla
    default List<EspecieListResponse> listarMapper(List<Object[]> e) {
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