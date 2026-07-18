package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Programacion.Projection.ProgramacionListarProjection;

public interface ProgramacionRepository extends JpaRepository<Programacion, Integer> {

    @Query(value = """
            SELECT idProgramacion, ambiente, descripcion
            FROM Programacion
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<ProgramacionCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
              p.idProgramacion AS idProgramacion,
              p.fecha AS fecha,
              ISNULL(ea.nombreEmpleado,'') + ' ' + ISNULL(ea.apellidoPaterno,'') AS nombreVeterinario,
              t.nombre AS nombreTurno,
              CONVERT(varchar(5), t.horaInicio, 108) AS horaInicio,
              CONVERT(varchar(5), t.horaFin, 108) AS horaFin,
              ep.nombre AS nombreEstadoProgramacion,
              p.ambiente AS ambiente,
              p.descripcion AS descripcion,
              p.fechaCreacion AS fechaCreacion
            FROM Programacion p
            LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoRegistrador = ea.idEmpleadoAsociado
            LEFT JOIN Turno t ON p.id_Turno = t.idTurno
            LEFT JOIN EstadoProgramacion ep ON p.id_EstadoProgramacion = ep.idEstadoProgramacion
            WHERE p.fechaEliminacion IS NULL AND p.id_Asociado = :idAsociado
              AND (:fecha = '' OR CAST(p.fecha AS DATE) = CAST(:fecha AS DATE))
              AND (:idEmpleadoRegistrador = 0 OR p.id_EmpleadoRegistrador = :idEmpleadoRegistrador)
            ORDER BY p.fecha DESC, t.horaInicio
            """, nativeQuery = true)
    List<ProgramacionListarProjection> listar(@Param("idAsociado") Integer idAsociado,
                                               @Param("fecha") String fecha,
                                               @Param("idEmpleadoRegistrador") Integer idEmpleadoRegistrador);

    @Query(value = """
            SELECT p.idProgramacion, p.fecha, p.id_Turno AS idTurno, p.id_EmpleadoRegistrador AS idEmpleadoRegistrador,
                   p.id_EstadoProgramacion AS idEstadoProgramacion, p.id_Categoria AS idCategoria,
                   p.id_Servicio AS idServicio, p.ambiente, p.descripcion,
                   CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
                   p.fechaCreacion,
                   CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
                   p.fechaModificacion,
                   CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
                   p.fechaEliminacion
            FROM Programacion AS p
            LEFT JOIN EmpleadoAsociado AS eac ON p.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON p.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON p.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE p.idProgramacion = :id AND p.id_Asociado = :idAsociado
            """, nativeQuery = true)
    ProgramacionDetalleProjection detalle(@Param("id") Integer id, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Programacion
            SET fechaEliminacion = GETDATE(), id_EmpleadoEliminador = :idUsuario
            WHERE idProgramacion = :id AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(@Param("id") Integer id, @Param("idUsuario") Integer idUsuario, @Param("idAsociado") Integer idAsociado);
}
