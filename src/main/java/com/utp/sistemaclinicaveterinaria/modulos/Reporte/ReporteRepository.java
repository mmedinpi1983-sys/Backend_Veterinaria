package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.CitaSemanaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.CitaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.DetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.IngresoCategoriaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.PacienteMesProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ProductoTopProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ResumenProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ResumenRangoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.VeterinarioProductividadProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Venta;

// Igual que en Dashboard: la entidad es solo un requisito de Spring Data, las consultas son nativas.
public interface ReporteRepository extends JpaRepository<Venta, Integer> {

    @Query(value = """
            SELECT
              (SELECT ISNULL(SUM(total),0) FROM Venta WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta,1)=1
                 AND MONTH(fechaVenta)=MONTH(GETDATE()) AND YEAR(fechaVenta)=YEAR(GETDATE())) AS ingresosMes,
              (SELECT ISNULL(SUM(total),0) FROM Venta WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta,1)=1
                 AND MONTH(fechaVenta)=MONTH(DATEADD(MONTH,-1,GETDATE())) AND YEAR(fechaVenta)=YEAR(DATEADD(MONTH,-1,GETDATE()))) AS ingresosMesAnterior,
              (SELECT COUNT(*) FROM CitaProgramada c JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL AND ec.nombre='Completado'
                 AND MONTH(c.fecha)=MONTH(GETDATE()) AND YEAR(c.fecha)=YEAR(GETDATE())) AS citasAtendidas,
              (SELECT COUNT(*) FROM CitaProgramada c JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL AND ec.nombre='Completado'
                 AND MONTH(c.fecha)=MONTH(DATEADD(MONTH,-1,GETDATE())) AND YEAR(c.fecha)=YEAR(DATEADD(MONTH,-1,GETDATE()))) AS citasAtendidasAnterior,
              (SELECT ISNULL(SUM(vd.cantidad),0) FROM VentaDetalle vd JOIN Venta v ON vd.id_Venta=v.idVenta
                 WHERE vd.fechaEliminacion IS NULL AND v.fechaEliminacion IS NULL AND ISNULL(v.estadoVenta,1)=1 AND vd.id_Producto IS NOT NULL
                 AND MONTH(v.fechaVenta)=MONTH(GETDATE()) AND YEAR(v.fechaVenta)=YEAR(GETDATE())) AS productosVendidos,
              (SELECT ISNULL(SUM(vd.cantidad),0) FROM VentaDetalle vd JOIN Venta v ON vd.id_Venta=v.idVenta
                 WHERE vd.fechaEliminacion IS NULL AND v.fechaEliminacion IS NULL AND ISNULL(v.estadoVenta,1)=1 AND vd.id_Producto IS NOT NULL
                 AND MONTH(v.fechaVenta)=MONTH(DATEADD(MONTH,-1,GETDATE())) AND YEAR(v.fechaVenta)=YEAR(DATEADD(MONTH,-1,GETDATE()))) AS productosVendidosAnterior,
              (SELECT COUNT(*) FROM Mascota WHERE fechaEliminacion IS NULL
                 AND MONTH(fechaCreacion)=MONTH(GETDATE()) AND YEAR(fechaCreacion)=YEAR(GETDATE())) AS pacientesNuevos,
              (SELECT COUNT(*) FROM Mascota WHERE fechaEliminacion IS NULL
                 AND MONTH(fechaCreacion)=MONTH(DATEADD(MONTH,-1,GETDATE())) AND YEAR(fechaCreacion)=YEAR(DATEADD(MONTH,-1,GETDATE()))) AS pacientesNuevosAnterior
            """, nativeQuery = true)
    ResumenProjection resumen();

    @Query(value = """
            SELECT CAST(c.fecha AS DATE) AS dia,
              SUM(CASE WHEN ec.nombre='Completado' THEN 1 ELSE 0 END) AS atendidas,
              SUM(CASE WHEN ec.nombre='Cancelado'  THEN 1 ELSE 0 END) AS canceladas
            FROM CitaProgramada c LEFT JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
            WHERE c.fechaEliminacion IS NULL
              AND c.fecha >= DATEADD(DAY, DATEDIFF(DAY,'1900-01-01',GETDATE())/7*7,'1900-01-01')
              AND c.fecha <  DATEADD(DAY,7, DATEADD(DAY, DATEDIFF(DAY,'1900-01-01',GETDATE())/7*7,'1900-01-01'))
            GROUP BY CAST(c.fecha AS DATE) ORDER BY dia
            """, nativeQuery = true)
    List<CitaSemanaProjection> citasSemana();

    @Query(value = """
            SELECT COALESCE(cat.nombre, CASE WHEN vd.id_Servicio IS NOT NULL THEN 'Servicios' ELSE 'Otros' END) AS categoria,
                   SUM(vd.subtotal) AS total
            FROM VentaDetalle vd
            JOIN Venta v ON vd.id_Venta=v.idVenta AND ISNULL(v.estadoVenta,1)=1 AND v.fechaEliminacion IS NULL
            LEFT JOIN Producto p ON vd.id_Producto=p.idProducto
            LEFT JOIN CategoriaProducto cat ON p.id_Categoria=cat.idCategoriaProducto
            WHERE vd.fechaEliminacion IS NULL
            GROUP BY COALESCE(cat.nombre, CASE WHEN vd.id_Servicio IS NOT NULL THEN 'Servicios' ELSE 'Otros' END)
            ORDER BY total DESC
            """, nativeQuery = true)
    List<IngresoCategoriaProjection> ingresosCategoria();

    @Query(value = """
            SELECT TOP 6 p.nombre AS nombre, SUM(vd.cantidad) AS cantidad
            FROM VentaDetalle vd
            JOIN Venta v ON vd.id_Venta=v.idVenta AND ISNULL(v.estadoVenta,1)=1 AND v.fechaEliminacion IS NULL
            JOIN Producto p ON vd.id_Producto=p.idProducto
            WHERE vd.fechaEliminacion IS NULL
            GROUP BY p.nombre ORDER BY SUM(vd.cantidad) DESC
            """, nativeQuery = true)
    List<ProductoTopProjection> productosTop();

    @Query(value = """
            SELECT CAST(DATEFROMPARTS(YEAR(fechaCreacion),MONTH(fechaCreacion),1) AS DATE) AS mes, COUNT(*) AS cantidad
            FROM Mascota
            WHERE fechaEliminacion IS NULL
              AND fechaCreacion >= DATEADD(MONTH,-5, DATEFROMPARTS(YEAR(GETDATE()),MONTH(GETDATE()),1))
            GROUP BY DATEFROMPARTS(YEAR(fechaCreacion),MONTH(fechaCreacion),1)
            ORDER BY mes
            """, nativeQuery = true)
    List<PacienteMesProjection> pacientesMes();

    @Query(value = """
            SELECT
              v.fechaVenta AS fecha,
              COALESCE(v.nombreComprador, LTRIM(RTRIM(CONCAT(d.nombre,' ',d.apellidoPaterno)))) AS cliente,
              (SELECT TOP 1 COALESCE(p.nombre, s.nombre)
                 FROM VentaDetalle vd
                 LEFT JOIN Producto p ON vd.id_Producto=p.idProducto
                 LEFT JOIN Servicio s ON vd.id_Servicio=s.idServicio
                 WHERE vd.id_Venta=v.idVenta) AS item,
              LTRIM(RTRIM(CONCAT(ea.nombreEmpleado,' ',ea.apellidoPaterno))) AS vendedor,
              v.total AS total
            FROM Venta v
            LEFT JOIN Dueno d ON v.id_Dueno=d.idDueno
            LEFT JOIN EmpleadoAsociado ea ON v.id_EmpleadoAsociado=ea.idEmpleadoAsociado
            WHERE v.fechaEliminacion IS NULL AND ISNULL(v.estadoVenta,1)=1
              AND (:fi = '' OR CAST(v.fechaVenta AS DATE) >= CAST(:fi AS DATE))
              AND (:ff = '' OR CAST(v.fechaVenta AS DATE) <= CAST(:ff AS DATE))
            ORDER BY v.fechaVenta DESC
            """, nativeQuery = true)
    List<DetalleProjection> detalle(@Param("fi") String fi, @Param("ff") String ff);

    // ===== KPIs del rango (para la hoja "Resumen" del Excel) =====
    @Query(value = """
            SELECT
              (SELECT ISNULL(SUM(total),0) FROM Venta
                 WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta,1)=1
                   AND (:fi='' OR CAST(fechaVenta AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(fechaVenta AS DATE)<=CAST(:ff AS DATE))) AS ingresos,
              (SELECT COUNT(*) FROM Venta
                 WHERE fechaEliminacion IS NULL AND ISNULL(estadoVenta,1)=1
                   AND (:fi='' OR CAST(fechaVenta AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(fechaVenta AS DATE)<=CAST(:ff AS DATE))) AS numVentas,
              (SELECT COUNT(*) FROM CitaProgramada c JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL AND ec.nombre='Completado'
                   AND (:fi='' OR CAST(c.fecha AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(c.fecha AS DATE)<=CAST(:ff AS DATE))) AS citasAtendidas,
              (SELECT COUNT(*) FROM CitaProgramada c JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
                 WHERE c.fechaEliminacion IS NULL AND ec.nombre='Cancelado'
                   AND (:fi='' OR CAST(c.fecha AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(c.fecha AS DATE)<=CAST(:ff AS DATE))) AS citasCanceladas,
              (SELECT ISNULL(SUM(vd.cantidad),0) FROM VentaDetalle vd JOIN Venta v ON vd.id_Venta=v.idVenta
                 WHERE vd.fechaEliminacion IS NULL AND v.fechaEliminacion IS NULL AND ISNULL(v.estadoVenta,1)=1 AND vd.id_Producto IS NOT NULL
                   AND (:fi='' OR CAST(v.fechaVenta AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(v.fechaVenta AS DATE)<=CAST(:ff AS DATE))) AS productosVendidos,
              (SELECT COUNT(*) FROM Mascota
                 WHERE fechaEliminacion IS NULL
                   AND (:fi='' OR CAST(fechaCreacion AS DATE)>=CAST(:fi AS DATE))
                   AND (:ff='' OR CAST(fechaCreacion AS DATE)<=CAST(:ff AS DATE))) AS pacientesNuevos
            """, nativeQuery = true)
    ResumenRangoProjection resumenRango(@Param("fi") String fi, @Param("ff") String ff);

    // ===== Detalle de citas del rango (hoja "Citas") =====
    @Query(value = """
            SELECT
              c.fecha AS fecha,
              m.nombre AS mascota,
              LTRIM(RTRIM(CONCAT(ISNULL(d.nombre,''),' ',ISNULL(d.apellidoPaterno,'')))) AS dueno,
              esp.nombre AS especie,
              raz.nombre AS raza,
              s.nombre AS servicio,
              LTRIM(RTRIM(CONCAT(ISNULL(ea.nombreEmpleado,''),' ',ISNULL(ea.apellidoPaterno,'')))) AS veterinario,
              ec.nombre AS estado,
              c.motivo AS motivo
            FROM CitaProgramada c
            LEFT JOIN Mascota m ON c.id_Mascota=m.idMascota
            LEFT JOIN Dueno d ON c.id_Dueno=d.idDueno
            LEFT JOIN EspecieRaza esp ON m.id_Especie=esp.idEspecieRaza
            LEFT JOIN EspecieRaza raz ON m.id_Raza=raz.idEspecieRaza
            LEFT JOIN Servicio s ON c.id_Servicio=s.idServicio
            LEFT JOIN Programacion pr ON c.id_Programacion=pr.idProgramacion
            LEFT JOIN EmpleadoAsociado ea ON pr.id_EmpleadoRegistrador=ea.idEmpleadoAsociado
            LEFT JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
            WHERE c.fechaEliminacion IS NULL
              AND (:fi='' OR CAST(c.fecha AS DATE)>=CAST(:fi AS DATE))
              AND (:ff='' OR CAST(c.fecha AS DATE)<=CAST(:ff AS DATE))
            ORDER BY c.fecha DESC
            """, nativeQuery = true)
    List<CitaDetalleProjection> detalleCitas(@Param("fi") String fi, @Param("ff") String ff);

    // ===== Productividad por veterinario en el rango (hoja "Veterinarios") =====
    @Query(value = """
            SELECT
              LTRIM(RTRIM(CONCAT(ISNULL(ea.nombreEmpleado,''),' ',ISNULL(ea.apellidoPaterno,'')))) AS veterinario,
              SUM(CASE WHEN ec.nombre='Completado' THEN 1 ELSE 0 END) AS atendidas,
              SUM(CASE WHEN ec.nombre='Cancelado'  THEN 1 ELSE 0 END) AS canceladas,
              COUNT(*) AS totalCitas
            FROM CitaProgramada c
            LEFT JOIN Programacion pr ON c.id_Programacion=pr.idProgramacion
            LEFT JOIN EmpleadoAsociado ea ON pr.id_EmpleadoRegistrador=ea.idEmpleadoAsociado
            LEFT JOIN EstadoCita ec ON c.id_EstadoCita=ec.idEstadoCita
            WHERE c.fechaEliminacion IS NULL
              AND (:fi='' OR CAST(c.fecha AS DATE)>=CAST(:fi AS DATE))
              AND (:ff='' OR CAST(c.fecha AS DATE)<=CAST(:ff AS DATE))
            GROUP BY LTRIM(RTRIM(CONCAT(ISNULL(ea.nombreEmpleado,''),' ',ISNULL(ea.apellidoPaterno,''))))
            ORDER BY atendidas DESC
            """, nativeQuery = true)
    List<VeterinarioProductividadProjection> productividadVeterinario(@Param("fi") String fi, @Param("ff") String ff);
}
