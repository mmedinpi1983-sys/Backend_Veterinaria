package com.utp.sistemaclinicaveterinaria.modulos.Categoria;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaListarProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriaMapper {

    @Mapping(target = "nombreCategoria", source = "nombreCategoria")
    CategoriaCatalogResponse CategoriaCatalogMapper(CategoriaCatalogoProjection projection);

    List<CategoriaCatalogResponse> CategoriaCatalogoMapperList(List<CategoriaCatalogoProjection> projections);

    CategoriaListResponse CategoriaListMapper(CategoriaListarProjection projection);

    List<CategoriaListResponse> CategoriaListMapperList(List<CategoriaListarProjection> projections);

    @Mapping(target = "usuarioCreador", source = "empleadoCreador")
    @Mapping(target = "usuarioModificador", source = "empleadoModificador")
    @Mapping(target = "usuarioEliminador", source = "empleadoEliminador")
    CategoriaDetailResponse CategoriaDetailMapper(CategoriaDetalleProjection projections);

    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Categoria toEntity(CategoriaCreateRequest dto);

    @Mapping(target = "idCategoria", ignore = true)
    @Mapping(target = "idAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Categoria entity, CategoriaUpdateRequest dto);
}
