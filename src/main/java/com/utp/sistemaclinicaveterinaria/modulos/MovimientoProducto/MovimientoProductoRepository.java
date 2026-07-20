package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.ClaseProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MotivoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MovimientoListarProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.MovimientoStatsProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.StockActualProjection;

public interface MovimientoProductoRepository extends JpaRepository<MovimientoProducto, Integer> {

    @Query(value = """
            SELECT
              mp.idMovimiento  AS idMovimiento,
              mp.fechaCreacion AS fecha,
              p.nombre         AS producto,
              cm.descripcion   AS tipo,
              mp.cantidad      AS cantidad,
              mp.stockAnterior AS stockAnterior,
              mp.stockNuevo    AS stockNuevo,
              mm.descripcion   AS motivo,
              LTRIM(RTRIM(CONCAT(ea.nombreEmpleado, ' ', ea.apellidoPaterno))) AS empleado
            FROM MovimientoProducto mp
            LEFT JOIN Producto p         ON mp.id_Producto = p.idProducto
            LEFT JOIN ClaseMovimiento cm ON mp.id_ClaseMovimiento = cm.idClaseMovimiento
            LEFT JOIN MotivoMovimiento mm ON mp.id_MotivoMovimiento = mm.idMotivoMovimiento
            LEFT JOIN EmpleadoAsociado ea ON mp.id_EmpleadoCreador = ea.idEmpleadoAsociado
            WHERE mp.fechaEliminacion IS NULL AND mp.id_Asociado = :idAsociado
              AND (:q = '' OR p.nombre LIKE CONCAT('%', :q, '%'))
              AND (:idClase = 0 OR mp.id_ClaseMovimiento = :idClase)
            ORDER BY mp.fechaCreacion DESC
            """, nativeQuery = true)
    List<MovimientoListarProjection> listar(@Param("idAsociado") Integer idAsociado,
                                            @Param("q") String q,
                                            @Param("idClase") Integer idClase);

    @Query(value = """
            SELECT
              SUM(CASE WHEN cm.descripcion LIKE '%ntrada%' THEN 1 ELSE 0 END) AS entradas,
              SUM(CASE WHEN cm.descripcion LIKE '%alida%'  THEN 1 ELSE 0 END) AS salidas,
              SUM(CASE WHEN cm.descripcion LIKE '%juste%'  THEN 1 ELSE 0 END) AS ajustes
            FROM MovimientoProducto mp
            LEFT JOIN ClaseMovimiento cm ON mp.id_ClaseMovimiento = cm.idClaseMovimiento
            WHERE mp.fechaEliminacion IS NULL AND mp.id_Asociado = :idAsociado
              AND MONTH(mp.fechaCreacion) = MONTH(GETDATE())
              AND YEAR(mp.fechaCreacion) = YEAR(GETDATE())
            """, nativeQuery = true)
    MovimientoStatsProjection stats(@Param("idAsociado") Integer idAsociado);

    @Query(value = "SELECT idClaseMovimiento AS idClaseMovimiento, descripcion AS descripcion FROM ClaseMovimiento ORDER BY idClaseMovimiento", nativeQuery = true)
    List<ClaseProjection> clases();

    @Query(value = "SELECT idMotivoMovimiento AS idMotivoMovimiento, descripcion AS descripcion FROM MotivoMovimiento ORDER BY descripcion", nativeQuery = true)
    List<MotivoProjection> motivos();

    @Query(value = "SELECT cantidadIngreso FROM Producto WHERE idProducto = :id", nativeQuery = true)
    Integer stockActual(@Param("id") Integer id);

    // Version en lote de stockActual: una sola consulta para todos los productos de una venta,
    // en vez de una consulta por cada linea de detalle.
    @Query(value = "SELECT idProducto AS idProducto, cantidadIngreso AS stock FROM Producto WHERE idProducto IN (:ids)", nativeQuery = true)
    List<StockActualProjection> stockActualEnLote(@Param("ids") List<Integer> ids);

    @Query(value = "SELECT descripcion FROM ClaseMovimiento WHERE idClaseMovimiento = :id", nativeQuery = true)
    String descripcionClase(@Param("id") Integer id);

    // Ancladas al inicio del texto (no "contains") para no matchear otras filas por casualidad,
    // p.ej. '%enta%' matcheaba tanto "Venta a cliente" como "Ajuste de inventario". El ORDER BY
    // deja el TOP 1 determinista si en el futuro dos descripciones comparten el mismo prefijo.
    @Query(value = "SELECT TOP 1 idClaseMovimiento FROM ClaseMovimiento WHERE descripcion LIKE 'Salida%' ORDER BY idClaseMovimiento", nativeQuery = true)
    Integer idClaseSalida();

    @Query(value = "SELECT TOP 1 idClaseMovimiento FROM ClaseMovimiento WHERE descripcion LIKE 'Entrada%' ORDER BY idClaseMovimiento", nativeQuery = true)
    Integer idClaseEntrada();

    @Query(value = "SELECT TOP 1 idMotivoMovimiento FROM MotivoMovimiento WHERE descripcion LIKE 'Venta%' ORDER BY idMotivoMovimiento", nativeQuery = true)
    Integer idMotivoVenta();

    @Query(value = "SELECT TOP 1 idMotivoMovimiento FROM MotivoMovimiento WHERE descripcion LIKE 'Devoluci%' ORDER BY idMotivoMovimiento", nativeQuery = true)
    Integer idMotivoDevolucion();

    @Modifying
    @Query(value = """
            UPDATE Producto SET cantidadIngreso = :nuevo, fechaModificacion = GETDATE(), id_EmpleadoModificador = :idUsuario
            WHERE idProducto = :id
            """, nativeQuery = true)
    void actualizarStock(@Param("id") Integer id, @Param("nuevo") Integer nuevo, @Param("idUsuario") Integer idUsuario);
}
