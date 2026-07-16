package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.CategoriaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoUpdateRequest;

public interface ProductoService {
    List<ProductoCatalogResponse> catalogo();
    List<ProductoListResponse> listar(ProductoFilterRequest f);
    List<CategoriaResponse> categorias();
    ProductoStatsResponse stats();
    ProductoDetailResponse obtenerId(Integer id);
    void crear(ProductoCreateRequest c);
    void actualizar(Integer id, ProductoUpdateRequest u);
    void eliminar(ProductoDeleteRequest d);
}
