package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface ProductoDTO {

    record ProductoCatalogResponse(
            Integer idProducto, String nombre, BigDecimal precioVenta, Integer stock) {
    }

    record ProductoListResponse(
            Integer idProducto, String nombre, String categoria, Integer idCategoria,
            Integer stock, Integer cantidadMinima, BigDecimal precioVenta,
            String proveedor, LocalDate fechaVencimiento, Boolean estado) {
    }

    record ProductoDetailResponse(
            Integer idProducto, String nombre, Integer idCategoria, String categoria,
            Integer stock, Integer cantidadMinima, BigDecimal precioVenta, BigDecimal precioCompra,
            String proveedor, LocalDate fechaVencimiento, String concentracion, String notas, Boolean estado) {
    }

    record ProductoStatsResponse(
            Integer items, Integer categorias, Integer bajoStock, BigDecimal valorTotal) {
    }

    record CategoriaResponse(Integer idCategoria, String nombreCategoria) {
    }

    record ProductoCreateRequest(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            @NotNull(message = "La categoría es obligatoria") Integer idCategoria,
            @NotNull(message = "El stock es obligatorio") Integer cantidadIngreso,
            @NotNull(message = "El stock mínimo es obligatorio") Integer cantidadMinima,
            BigDecimal precioVenta, BigDecimal precioCompra,
            String proveedor, LocalDate fechaVencimiento, String concentracion, String notas) {
    }

    record ProductoUpdateRequest(
            String nombre, Integer idCategoria, Integer cantidadIngreso, Integer cantidadMinima,
            BigDecimal precioVenta, BigDecimal precioCompra, String proveedor,
            LocalDate fechaVencimiento, String concentracion, String notas, Boolean estado) {
    }

    record ProductoFilterRequest(String q, Integer idCategoria) {
    }

    record ProductoDeleteRequest(Integer idProducto) {
    }
}
