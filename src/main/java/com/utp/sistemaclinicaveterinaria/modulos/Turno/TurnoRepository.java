package com.utp.sistemaclinicaveterinaria.modulos.Turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.Projection.TurnoListarProjection;

import java.util.Optional;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
        List<Turno> findByFechaEliminacionIsNull();

        Optional<Turno> findByIdTurnoAndFechaEliminacionIsNull(Integer idTurno);

        // catalogo de turnos
        @Query(value = """
                        SELECT
                        idturno,
                        CONCAT(
                        nombre,' (', CONVERT(VARCHAR(5),horaInicio,108), ' - ', CONVERT(VARCHAR(5),horaFin,108),')'
                        ) AS nombre
                        FROM Turno
                        WHERE estado = 1 AND
                        id_Asociado = :idAsociado
                        """, nativeQuery = true)
        List<TurnoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

        // lista para tabla
        @Query(value = """
                        SELECT
                        idTurno,
                        nombre,
                        horaInicio,
                        horaFin,
                        fechaCreacion,
                        estado
                        FROM Turno
                        WHERE
                        (:nombre IS NULL OR nombre LIKE CONCAT('%', :nombre , '%')) AND
                        (:estado IS NULL OR estado = :estado) AND
                        id_Asociado = :idAsociado
                        """, nativeQuery = true)
        List<TurnoListarProjection> listar(
                        @Param("nombre") String nombre,
                        @Param("estado") Boolean estado,
                        @Param("idAsociado") Integer idAsociado);

        // detalle de objeto
        @Query(value = """
                        SELECT
                        t.nombre,
                        t.horaInicio,
                        t.horaFin,
                        t.estado,
                        CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
                        t.fechaCreacion,
                        CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
                        t.fechaModificacion,
                        CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
                        t.fechaEliminacion
                        FROM Turno  AS t
                        LEFT JOIN EmpleadoAsociado AS eac ON t.id_EmpleadoCreador = eac.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eam ON t.id_EmpleadoModificador = eam.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eae ON t.id_EmpleadoEliminador = eae.idEmpleadoAsociado
                        WHERE t.idTurno = :idTurno AND t.id_Asociado = :idAsociado
                        """, nativeQuery = true)
        TurnoDetalleProjection detalle(
                        @Param("idTurno") Integer idTurno,
                        @Param("idAsociado") Integer idAsociado);

        // eliminar
        @Modifying
        @Transactional
        @Query(value = """
                        UPDATE Turno
                        SET
                        estado = 0,
                        id_EmpleadoEliminador = :idUsuarioEliminador,
                        fechaModificacion = GETDATE()
                        WHERE idTurno = :idTurno and id_Asociado = :idAsociado
                                    """, nativeQuery = true)
        void eliminar(
                        @Param("idTurno") Integer idTurno,
                        @Param("idUsuarioEliminador") Integer idUsuarioEliminador,
                        @Param("idAsociado") Integer idAsociado);

}
