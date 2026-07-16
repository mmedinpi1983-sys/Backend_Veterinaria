package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.CategoriaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.Projection.ProductoStatsProjection;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = """
            SELECT
              p.idProducto      AS idProducto,
              p.nombre          AS nombre,
              c.nombreCategoria AS categoria,
              p.id_Categoria    AS idCategoria,
              p.cantidadIngreso AS stock,
              p.cantidadMinima  AS cantidadMinima,
              p.precioVenta     AS precioVenta,
              p.proveedor       AS proveedor,
              p.fechaVencimiento AS fechaVencimiento,
              p.estado          AS estado
            FROM Producto p
            LEFT JOIN Categoria c ON p.id_Categoria = c.idCategoria
            LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
            WHERE p.fechaEliminacion IS NULL AND ea.id_Asociado = :idAsociado
              AND (:q = '' OR p.nombre LIKE CONCAT('%', :q, '%'))
              AND (:idCategoria = 0 OR p.id_Categoria = :idCategoria)
            ORDER BY p.nombre
            """, nativeQuery = true)
    List<ProductoListarProjection> listar(@Param("idAsociado") Integer idAsociado,
                                          @Param("q") String q,
                                          @Param("idCategoria") Integer idCategoria);

    @Query(value = """
            SELECT
              p.idProducto      AS idProducto,
              p.nombre          AS nombre,
              p.precioVenta     AS precioVenta,
              p.cantidadIngreso AS stock
            FROM Producto p
            LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
            WHERE p.fechaEliminacion IS NULL AND p.estado = 1 AND ea.id_Asociado = :idAsociado
            ORDER BY p.nombre
            """, nativeQuery = true)
    List<ProductoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
              p.idProducto AS idProducto, p.nombre AS nombre, p.id_Categoria AS idCategoria,
              c.nombreCategoria AS categoria, p.cantidadIngreso AS stock, p.cantidadMinima AS cantidadMinima,
              p.precioVenta AS precioVenta, p.precioCompra AS precioCompra, p.proveedor AS proveedor,
              p.fechaVencimiento AS fechaVencimiento, p.concentracion AS concentracion,
              p.notas AS notas, p.estado AS estado
            FROM Producto p
            LEFT JOIN Categoria c ON p.id_Categoria = c.idCategoria
            WHERE p.idProducto = :id
            """, nativeQuery = true)
    ProductoDetalleProjection detalle(@Param("id") Integer id);

    @Query(value = """
            SELECT
              (SELECT COUNT(*) FROM Producto p JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
                 WHERE p.fechaEliminacion IS NULL AND p.estado = 1 AND ea.id_Asociado = :idAsociado) AS items,
              (SELECT COUNT(DISTINCT p.id_Categoria) FROM Producto p JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
                 WHERE p.fechaEliminacion IS NULL AND p.estado = 1 AND ea.id_Asociado = :idAsociado) AS categorias,
              (SELECT COUNT(*) FROM Producto p JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
                 WHERE p.fechaEliminacion IS NULL AND p.estado = 1 AND ea.id_Asociado = :idAsociado
                   AND p.cantidadIngreso <= p.cantidadMinima) AS bajoStock,
              (SELECT ISNULL(SUM(p.cantidadIngreso * p.precioVenta), 0) FROM Producto p JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
                 WHERE p.fechaEliminacion IS NULL AND p.estado = 1 AND ea.id_Asociado = :idAsociado) AS valorTotal
            """, nativeQuery = true)
    ProductoStatsProjection stats(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT idCategoria AS idCategoria, nombreCategoria AS nombreCategoria
            FROM Categoria
            WHERE estado = 1 AND id_Asociado = :idAsociado
            ORDER BY nombreCategoria
            """, nativeQuery = true)
    List<CategoriaProjection> categorias(@Param("idAsociado") Integer idAsociado);

    @Modifying
    @Query(value = """
            UPDATE Producto SET estado = 0, fechaEliminacion = GETDATE(), id_EmpleadoEliminador = :idUsuario
            WHERE idProducto = :id
            """, nativeQuery = true)
    void eliminar(@Param("id") Integer id, @Param("idUsuario") Integer idUsuario);
}
