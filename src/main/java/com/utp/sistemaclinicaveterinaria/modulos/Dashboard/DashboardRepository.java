package com.utp.sistemaclinicaveterinaria.modulos.Dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.AlertaStockProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.CitaSemanaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.ResumenProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Venta;

// Se cuelga de la entidad Venta solo porque Spring Data pide una; las queries son todas nativas.
public interface DashboardRepository extends JpaRepository<Venta, Integer> {

    @Query(value = """
            SELECT
              (SELECT COUNT(*) FROM CitaProgramada c
                 WHERE c.fechaEliminacion IS NULL
                   AND CAST(c.fecha AS DATE) = CAST(GETDATE() AS DATE)) AS citasHoyTotal,
              (SELECT COUNT(*) FROM CitaProgramada c
                 LEFT JOIN EstadoCita ec ON c.id_EstadoCita = ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL
                   AND CAST(c.fecha AS DATE) = CAST(GETDATE() AS DATE)
                   AND ec.nombre = 'Completado') AS citasHoyCompletadas,
              (SELECT COUNT(*) FROM CitaProgramada c
                 LEFT JOIN EstadoCita ec ON c.id_EstadoCita = ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL
                   AND CAST(c.fecha AS DATE) = CAST(GETDATE() AS DATE)
                   AND ec.nombre = 'Pendiente') AS citasHoyPendientes,
              (SELECT COUNT(*) FROM Mascota
                 WHERE fechaEliminacion IS NULL AND estado = 1) AS pacientesActivos,
              (SELECT COUNT(*) FROM Mascota
                 WHERE fechaEliminacion IS NULL
                   AND MONTH(fechaCreacion) = MONTH(GETDATE())
                   AND YEAR(fechaCreacion) = YEAR(GETDATE())) AS pacientesNuevosMes,
              (SELECT ISNULL(SUM(total), 0) FROM Venta
                 WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta, 1) = 1
                   AND CAST(fechaVenta AS DATE) = CAST(GETDATE() AS DATE)) AS ingresosHoy,
              (SELECT ISNULL(SUM(total), 0) FROM Venta
                 WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta, 1) = 1
                   AND CAST(fechaVenta AS DATE) = CAST(DATEADD(DAY, -1, GETDATE()) AS DATE)) AS ingresosAyer,
              (SELECT COUNT(*) FROM Producto
                 WHERE estado = 1 AND cantidadIngreso <= cantidadMinima) AS alertasStock
            """, nativeQuery = true)
    ResumenProjection resumen();

    @Query(value = """
            SELECT CAST(c.fecha AS DATE) AS dia, COUNT(*) AS cantidad
            FROM CitaProgramada c
            WHERE c.fechaEliminacion IS NULL
              AND c.fecha >= DATEADD(DAY, DATEDIFF(DAY, '1900-01-01', GETDATE()) / 7 * 7, '1900-01-01')
              AND c.fecha <  DATEADD(DAY, 7, DATEADD(DAY, DATEDIFF(DAY, '1900-01-01', GETDATE()) / 7 * 7, '1900-01-01'))
            GROUP BY CAST(c.fecha AS DATE)
            ORDER BY dia
            """, nativeQuery = true)
    List<CitaSemanaProjection> citasSemana();

    @Query(value = """
            SELECT
              p.nombre          AS nombre,
              c.nombreCategoria AS categoria,
              p.cantidadIngreso AS stock,
              p.cantidadMinima  AS minimo
            FROM Producto p
            LEFT JOIN Categoria c ON p.id_Categoria = c.idCategoria
            WHERE p.estado = 1 AND p.cantidadIngreso <= p.cantidadMinima
            ORDER BY p.cantidadIngreso ASC
            """, nativeQuery = true)
    List<AlertaStockProjection> alertasStock();
}
