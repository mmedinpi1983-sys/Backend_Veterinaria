package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.time.LocalDateTime;
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

@Mapper(componentModel = "spring")
public interface RazaMapper {
    // objeto del catalogo
    default RazaCatalogResponse RazaCatalogMapper(Object[] r) {
        return new RazaCatalogResponse(
                (Integer) r[0],
                (String) r[1],
                (Integer) r[2]);
    }

    // objeto que se mostrara en la lista
    default RazaListResponse RazaListMapper(Object[] r) {
        return new RazaListResponse(
                (Integer) r[0],
                (String) r[1],
                (String) r[2],
                (Boolean) r[3],
                (LocalDateTime) r[4]);
    }

    // objeto completo que se mostrara
    default RazaDetailResponse RazaDetailMapper(Object[] row) {
        return new RazaDetailResponse(
                (Integer) row[0], // idEspecieRaza
                (String) row[1], // nombre
                (Integer) row[2], // id_Especie
                (String) row[3], // nombreEspecie
                (Boolean) row[4], // estado — ojo, el índice 4
                (String) row[5], // empleadoCreador
                (LocalDateTime) row[6], // fechaCreacion
                (String) row[7], // empleadoModificador
                (LocalDateTime) row[8],
                (String) row[9], // empleadoEliminador
                (LocalDateTime) row[10]);
    }

    // catalogo lista
    default List<RazaCatalogResponse> catalogoMapper(List<Object[]> r) {
        return r.stream().map(this::RazaCatalogMapper).toList();
    }

    // filas de tabla
    default List<RazaListResponse> listarMapper(List<Object[]> r) {
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