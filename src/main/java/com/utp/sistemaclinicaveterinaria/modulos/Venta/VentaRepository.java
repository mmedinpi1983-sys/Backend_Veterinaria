package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.MetodoPagoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.ProductoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaLineaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaListarProjection;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = """
            SELECT
            v.idVenta        AS idVenta,
            v.codigoVenta    AS codigoVenta,
            v.fechaVenta     AS fechaVenta,
            COALESCE(v.nombreComprador,
                     LTRIM(RTRIM(CONCAT(d.nombre, ' ', d.apellidoPaterno, ' ', d.apellidoMaterno)))) AS cliente,
            v.tipoComprobante AS tipoComprobante,
            mp.nombre        AS metodoPago,
            v.total          AS total,
            v.estadoVenta    AS estadoVenta
            FROM Venta v
            LEFT JOIN Dueno d      ON v.id_Dueno = d.idDueno
            LEFT JOIN MetodoPago mp ON v.id_MetodoPago = mp.idMetodoPago
            LEFT JOIN EmpleadoAsociado ea ON v.id_EmpleadoAsociado = ea.idEmpleadoAsociado
            WHERE v.fechaEliminacion IS NULL
              AND ea.id_Asociado = :idAsociado
              AND (:q = '' OR v.codigoVenta LIKE CONCAT('%', :q, '%')
                          OR v.nombreComprador LIKE CONCAT('%', :q, '%')
                          OR d.nombre LIKE CONCAT('%', :q, '%'))
              AND (:tipo = '' OR v.tipoComprobante = :tipo)
            ORDER BY v.fechaVenta DESC
            """, nativeQuery = true)
    List<VentaListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("q") String q,
            @Param("tipo") String tipo);

    @Query(value = """
            SELECT
            v.idVenta         AS idVenta,
            v.codigoVenta     AS codigoVenta,
            v.id_Dueno        AS idDueno,
            COALESCE(v.nombreComprador,
                     LTRIM(RTRIM(CONCAT(d.nombre, ' ', d.apellidoPaterno, ' ', d.apellidoMaterno)))) AS nombreComprador,
            v.dniComprador    AS dniComprador,
            v.tipoComprobante AS tipoComprobante,
            v.fechaVenta      AS fechaVenta,
            v.subTotal        AS subTotal,
            v.descuento       AS descuento,
            v.igv             AS igv,
            v.total           AS total,
            v.montoPagado     AS montoPagado,
            v.estadoVenta     AS estadoVenta,
            v.id_MetodoPago   AS idMetodoPago,
            mp.nombre         AS metodoPago
            FROM Venta v
            LEFT JOIN Dueno d       ON v.id_Dueno = d.idDueno
            LEFT JOIN MetodoPago mp ON v.id_MetodoPago = mp.idMetodoPago
            WHERE v.idVenta = :idVenta
            """, nativeQuery = true)
    VentaDetalleProjection detalle(@Param("idVenta") Integer idVenta);

    @Query(value = """
            SELECT
            vd.idVentaDetalle AS idVentaDetalle,
            COALESCE(p.nombre, s.nombre) AS nombreItem,
            CASE WHEN vd.id_Producto IS NOT NULL THEN 'Producto' ELSE 'Servicio' END AS tipo,
            vd.cantidad       AS cantidad,
            vd.precioUnitario AS precioUnitario,
            vd.subtotal       AS subtotal
            FROM VentaDetalle vd
            LEFT JOIN Producto p ON vd.id_Producto = p.idProducto
            LEFT JOIN Servicio s ON vd.id_Servicio = s.idServicio
            WHERE vd.id_Venta = :idVenta AND vd.fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<VentaLineaProjection> lineas(@Param("idVenta") Integer idVenta);

    @Query(value = """
            SELECT
            p.idProducto     AS idProducto,
            p.nombre         AS nombre,
            p.precioVenta    AS precioVenta,
            c.nombreCategoria AS categoria
            FROM Producto p
            LEFT JOIN Categoria c ON p.id_Categoria = c.idCategoria
            LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoAsociado = ea.idEmpleadoAsociado
            WHERE p.estado = 1 AND ea.id_Asociado = :idAsociado
            ORDER BY p.nombre
            """, nativeQuery = true)
    List<ProductoCatalogoProjection> productosCatalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT idMetodoPago AS idMetodoPago, nombre AS nombre
            FROM MetodoPago
            WHERE estado = 1
            ORDER BY nombre
            """, nativeQuery = true)
    List<MetodoPagoProjection> metodosPago();

    @Query(value = """
            SELECT COUNT(1)
            FROM Venta v
            LEFT JOIN EmpleadoAsociado ea ON v.id_EmpleadoAsociado = ea.idEmpleadoAsociado
            WHERE ea.id_Asociado = :idAsociado AND ISNULL(v.tipoComprobante, '') = :tipo
            """, nativeQuery = true)
    Integer contarPorTipo(@Param("idAsociado") Integer idAsociado, @Param("tipo") String tipo);

    @Modifying
    @Query(value = """
            UPDATE Venta
            SET estadoVenta = 0,
                id_EmpleadoEliminador = :idUsuario,
                fechaEliminacion = GETDATE()
            WHERE idVenta = :idVenta
            """, nativeQuery = true)
    void anular(@Param("idVenta") Integer idVenta, @Param("idUsuario") Integer idUsuario);
}
