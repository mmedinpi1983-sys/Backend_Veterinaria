package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.CategoriaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.CategoriaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoStatsProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductoMapper {

    ProductoListResponse toList(ProductoListarProjection p);
    List<ProductoListResponse> toList(List<ProductoListarProjection> p);

    ProductoCatalogResponse toCatalog(ProductoCatalogoProjection p);
    List<ProductoCatalogResponse> toCatalog(List<ProductoCatalogoProjection> p);

    ProductoDetailResponse toDetail(ProductoDetalleProjection p);

    ProductoStatsResponse toStats(ProductoStatsProjection p);

    CategoriaResponse toCategoria(CategoriaProjection p);
    List<CategoriaResponse> toCategoria(List<CategoriaProjection> p);

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "fechaFabricacion", ignore = true)
    @Mapping(target = "requiereReceta", ignore = true)
    @Mapping(target = "idPresentacion", ignore = true)
    @Mapping(target = "requiereInventario", ignore = true)
    @Mapping(target = "idEmpleadoAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    Producto toEntity(ProductoCreateRequest dto);

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "fechaFabricacion", ignore = true)
    @Mapping(target = "requiereReceta", ignore = true)
    @Mapping(target = "idPresentacion", ignore = true)
    @Mapping(target = "requiereInventario", ignore = true)
    @Mapping(target = "idEmpleadoAsociado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "fechaEliminacion", ignore = true)
    @Mapping(target = "idEmpleadoCreador", ignore = true)
    @Mapping(target = "idEmpleadoModificador", ignore = true)
    @Mapping(target = "idEmpleadoEliminador", ignore = true)
    void updateEntity(@MappingTarget Producto entity, ProductoUpdateRequest dto);
}
