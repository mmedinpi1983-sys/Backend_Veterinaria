package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaUpdateRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConfiguracionSistemaMapper {

    ConfiguracionSistemaResponse toResponse(ConfiguracionSistema entity);

    @Mapping(target = "idConfiguracionSistema", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    ConfiguracionSistema toEntity(ConfiguracionSistemaCreateRequest dto);

    @Mapping(target = "idConfiguracionSistema", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    void updateEntity(@MappingTarget ConfiguracionSistema entity, ConfiguracionSistemaUpdateRequest dto);
}
