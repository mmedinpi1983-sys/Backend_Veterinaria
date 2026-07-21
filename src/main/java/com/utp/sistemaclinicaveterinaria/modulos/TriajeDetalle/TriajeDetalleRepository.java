package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle.Projection.TriajeDetalleListarProjection;

public interface TriajeDetalleRepository extends JpaRepository<TriajeDetalle, Integer> {

    @Query(value = """
            SELECT
            idTriajeDetalle,
            observaciones
            FROM TriajeDetalle
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<TriajeDetalleCatalogoProjection> catalogo();

    @Query(value = """
            SELECT
            idTriajeDetalle,
            observaciones,
            alergias,
            fechaCreacion
            FROM TriajeDetalle
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<TriajeDetalleListarProjection> listar();

    @Query(value = """
            SELECT
            td.idTriajeDetalle,
            td.temperatura,
            td.peso,
            td.observaciones,
            td.alergias,
            td.id_Triaje AS idTriaje,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            td.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            td.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            td.fechaEliminacion
            FROM TriajeDetalle AS td
            LEFT JOIN EmpleadoAsociado AS eac ON td.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON td.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON td.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE td.idTriajeDetalle = :idTriajeDetalle
            """, nativeQuery = true)
    TriajeDetalleDetalleProjection detalle(@Param("idTriajeDetalle") Integer idTriajeDetalle);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE TriajeDetalle
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idTriajeDetalle = :idTriajeDetalle
            """, nativeQuery = true)
    void eliminar(
            @Param("idTriajeDetalle") Integer idTriajeDetalle,
            @Param("idUsuario") Integer idUsuario);

    Optional<TriajeDetalle> findByIdTriajeAndFechaEliminacionIsNull(Integer idTriaje);
}
