package com.utp.sistemaclinicaveterinaria.modulos.Triaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Triaje.Projection.TriajeListarProjection;

public interface TriajeRepository extends JpaRepository<Triaje, Integer> {

    @Query(value = """
            SELECT
            idTriaje,
            codigoTemporal,
            prioridad,
            estado
            FROM Triaje
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<TriajeCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idTriaje,
            codigoTemporal,
            estado,
            fechaCreacion
            FROM Triaje
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<TriajeListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            t.idTriaje,
            t.id_CitaProgramada AS idCitaProgramada,
            t.codigoTemporal,
            t.id_Mascota AS idMascota,
            t.prioridad,
            t.estado,
            t.id_MetodoIngreso AS idMetodoIngreso,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            t.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            t.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            t.fechaEliminacion
            FROM Triaje AS t
            LEFT JOIN EmpleadoAsociado AS eac ON t.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON t.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON t.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE t.idTriaje = :idTriaje AND t.id_Asociado = :idAsociado
            """, nativeQuery = true)
    TriajeDetalleProjection detalle(@Param("idTriaje") Integer idTriaje, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Triaje
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idTriaje = :idTriaje AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idTriaje") Integer idTriaje,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);

    Optional<Triaje> findByIdTriajeAndFechaEliminacionIsNull(Integer idTriaje);

    Optional<Triaje> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);
}
