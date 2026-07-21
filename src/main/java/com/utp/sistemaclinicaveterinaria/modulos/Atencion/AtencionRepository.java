package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection.AtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.Projection.AtencionListarProjection;

public interface AtencionRepository extends JpaRepository<Atencion, Integer> {

    @Query(value = """
            SELECT
            a.idAtencion,
            a.observacion,
            a.fechaCreacion
            FROM Atencion AS a
            WHERE a.fechaEliminacion IS NULL
            ORDER BY a.fechaCreacion DESC
            """, nativeQuery = true)
    List<AtencionListarProjection> listar();

    @Query(value = """
            SELECT
            a.idAtencion,
            a.id_CitaProgramada AS idCitaProgramada,
            a.id_Triaje AS idTriaje,
            a.fechaAtencion,
            a.horaInicio,
            a.horaFin,
            a.observacion,
            a.id_EstadoSalida AS idEstadoSalida,
            a.id_EstadoAtencion AS idEstadoAtencion,
            a.id_Mascota AS idMascota,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            a.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            a.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            a.fechaEliminacion
            FROM Atencion AS a
            LEFT JOIN EmpleadoAsociado AS eac ON a.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON a.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON a.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE a.idAtencion = :idAtencion
            """, nativeQuery = true)
    AtencionDetalleProjection detalle(@Param("idAtencion") Integer idAtencion);

    Optional<Atencion> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Atencion
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idAtencion = :idAtencion
            """, nativeQuery = true)
    void eliminar(
            @Param("idAtencion") Integer idAtencion,
            @Param("idUsuario") Integer idUsuario);

    @Query(value = """
        SELECT
            a.idAtencion,
            CONVERT(VARCHAR, a.fechaAtencion, 23) AS fechaAtencion,
            ISNULL(ac.evaluacionClinica, '') AS evaluacionClinica,
            ISNULL(ac.tratamiento, '') AS tratamiento,
            ISNULL(CONVERT(VARCHAR, ac.fechaProximoControl, 23), '') AS fechaProximoControl,
            ISNULL(ac.motivoConsulta, '') AS motivoConsulta
        FROM Atencion a
        LEFT JOIN AtencionConsulta ac ON a.idAtencion = ac.id_Atencion AND ac.fechaEliminacion IS NULL
        WHERE a.id_Mascota = :idMascota AND a.fechaEliminacion IS NULL
        ORDER BY a.fechaAtencion DESC
        """, nativeQuery = true)
    List<HistorialView> findHistorialByIdMascota(@Param("idMascota") Integer idMascota);
}
