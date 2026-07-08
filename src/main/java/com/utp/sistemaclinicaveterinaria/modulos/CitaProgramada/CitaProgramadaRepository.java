package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection.CitaProgramadaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada.Projection.CitaProgramadaListarProjection;

public interface CitaProgramadaRepository extends JpaRepository<CitaProgramada, Integer> {

    @Query(value = """
            SELECT
            cp.idCitaProgramada,
            cp.motivo,
            cp.motivoReprogramacion,
            cp.fechaCreacion
            FROM CitaProgramada AS cp
            WHERE cp.fechaEliminacion IS NULL
            ORDER BY cp.fechaCreacion DESC
            """, nativeQuery = true)
    List<CitaProgramadaListarProjection> listar();

    @Query(value = """
            SELECT
            cp.idCitaProgramada,
            cp.id_Dueno AS idDueno,
            cp.id_Programacion AS idProgramacion,
            cp.id_Mascota AS idMascota,
            cp.fecha,
            cp.horaInicio,
            cp.horaFin,
            cp.id_EstadoCita AS idEstadoCita,
            cp.motivo,
            cp.id_Categoria AS idCategoria,
            cp.id_Servicio AS idServicio,
            cp.motivoReprogramacion,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            cp.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            cp.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            cp.fechaEliminacion
            FROM CitaProgramada AS cp
            LEFT JOIN EmpleadoAsociado AS eac ON cp.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON cp.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON cp.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE cp.idCitaProgramada = :idCitaProgramada
            """, nativeQuery = true)
    CitaProgramadaDetalleProjection detalle(@Param("idCitaProgramada") Integer idCitaProgramada);

    Optional<CitaProgramada> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);

    @Query(value = """
            UPDATE CitaProgramada
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idCitaProgramada = :idCitaProgramada
            """, nativeQuery = true)
    void eliminar(
            @Param("idCitaProgramada") Integer idCitaProgramada,
            @Param("idUsuario") Integer idUsuario);

    @Query(value = """
        SELECT
            c.idCitaProgramada,
            c.fecha,
            c.horaInicio,
            c.horaFin,
            m.nombre AS nombreMascota,
            er_esp.nombre AS especie,
            er_raza.nombre AS raza,
            ISNULL(d.nombre,'') + ' ' + ISNULL(d.apellidoPaterno,'') + ' ' + ISNULL(d.apellidoMaterno,'') AS nombreDueno,
            s.nombre AS nombreServicio,
            ISNULL(ea.nombreEmpleado,'') + ' ' + ISNULL(ea.apellidoPaterno,'') AS nombreVeterinario,
            ec.nombre AS estadoCita,
            c.id_EstadoCita AS idEstadoCita,
            c.motivo
        FROM CitaProgramada c
        LEFT JOIN Mascota m ON c.id_Mascota = m.idMascota
        LEFT JOIN EspecieRaza er_esp ON m.id_Especie = er_esp.idEspecieRaza
        LEFT JOIN EspecieRaza er_raza ON m.id_Raza = er_raza.idEspecieRaza
        LEFT JOIN Dueno d ON c.id_Dueno = d.idDueno
        LEFT JOIN Servicio s ON c.id_Servicio = s.idServicio
        LEFT JOIN Programacion p ON c.id_Programacion = p.idProgramacion
        LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoRegistrador = ea.idEmpleadoAsociado
        LEFT JOIN EstadoCita ec ON c.id_EstadoCita = ec.idEstadoCita
        WHERE c.fechaEliminacion IS NULL
        AND (:idEstado IS NULL OR c.id_EstadoCita = :idEstado)
        AND (:idEmpleado IS NULL OR p.id_EmpleadoRegistrador = :idEmpleado)
        AND (:fechaInicio IS NULL OR c.fecha >= CAST(:fechaInicio AS DATE))
        AND (:fechaFin IS NULL OR c.fecha <= CAST(:fechaFin AS DATE))
        AND (:busqueda IS NULL OR m.nombre LIKE '%' + :busqueda + '%' OR d.nombre LIKE '%' + :busqueda + '%')
        ORDER BY c.fecha DESC, c.horaInicio ASC
        """, nativeQuery = true)
    List<CitaListView> findCitasFiltradas(
        @Param("idEstado") Integer idEstado,
        @Param("idEmpleado") Integer idEmpleado,
        @Param("fechaInicio") String fechaInicio,
        @Param("fechaFin") String fechaFin,
        @Param("busqueda") String busqueda
    );

    @Query(value = """
        SELECT
            c.idCitaProgramada,
            c.fecha,
            c.horaInicio,
            c.horaFin,
            m.nombre AS nombreMascota,
            er_esp.nombre AS especie,
            er_raza.nombre AS raza,
            ISNULL(d.nombre,'') + ' ' + ISNULL(d.apellidoPaterno,'') + ' ' + ISNULL(d.apellidoMaterno,'') AS nombreDueno,
            s.nombre AS nombreServicio,
            ISNULL(ea.nombreEmpleado,'') + ' ' + ISNULL(ea.apellidoPaterno,'') AS nombreVeterinario,
            ec.nombre AS estadoCita,
            c.id_EstadoCita AS idEstadoCita,
            c.motivo
        FROM CitaProgramada c
        LEFT JOIN Mascota m ON c.id_Mascota = m.idMascota
        LEFT JOIN EspecieRaza er_esp ON m.id_Especie = er_esp.idEspecieRaza
        LEFT JOIN EspecieRaza er_raza ON m.id_Raza = er_raza.idEspecieRaza
        LEFT JOIN Dueno d ON c.id_Dueno = d.idDueno
        LEFT JOIN Servicio s ON c.id_Servicio = s.idServicio
        LEFT JOIN Programacion p ON c.id_Programacion = p.idProgramacion
        LEFT JOIN EmpleadoAsociado ea ON p.id_EmpleadoRegistrador = ea.idEmpleadoAsociado
        LEFT JOIN EstadoCita ec ON c.id_EstadoCita = ec.idEstadoCita
        WHERE c.fechaEliminacion IS NULL AND c.id_Mascota = :idMascota
        ORDER BY c.fecha DESC, c.horaInicio ASC
        """, nativeQuery = true)
    List<CitaListView> findCitasByMascota(@Param("idMascota") Integer idMascota);

    @Query(value = """
        SELECT
            MIN(p.idProgramacion) AS idProgramacion,
            ISNULL(ea.nombreEmpleado,'') + ' ' + ISNULL(ea.apellidoPaterno,'') AS nombreVeterinario,
            t.nombre AS nombreTurno,
            CONVERT(varchar(5), t.horaInicio, 108) AS horaInicio,
            CONVERT(varchar(5), t.horaFin, 108) AS horaFin
        FROM Programacion p
        INNER JOIN EmpleadoAsociado ea ON p.id_EmpleadoRegistrador = ea.idEmpleadoAsociado
        INNER JOIN Turno t ON p.id_Turno = t.idTurno
        WHERE p.fechaEliminacion IS NULL AND ea.fechaEliminacion IS NULL AND t.fechaEliminacion IS NULL
        GROUP BY ea.idEmpleadoAsociado, ea.nombreEmpleado, ea.apellidoPaterno, t.nombre, t.horaInicio, t.horaFin
        ORDER BY t.horaInicio
        """, nativeQuery = true)
    List<VeterinarioView> findVeterinariosDisponibles();

    @Query(value = """
        SELECT CONVERT(varchar(5), c.horaInicio, 108)
        FROM CitaProgramada c
        WHERE c.fechaEliminacion IS NULL
          AND c.id_Programacion = :idProgramacion
          AND c.fecha = CAST(:fecha AS DATE)
          AND (c.id_EstadoCita IS NULL OR c.id_EstadoCita <> 3)
          AND (:excluirId IS NULL OR c.idCitaProgramada <> :excluirId)
        """, nativeQuery = true)
    List<String> findHorasOcupadas(@Param("idProgramacion") Integer idProgramacion, @Param("fecha") String fecha, @Param("excluirId") Integer excluirId);

    @Query(value = """
        SELECT
            COUNT(*) AS total,
            SUM(CASE WHEN ec.nombre = 'Pendiente'  THEN 1 ELSE 0 END) AS pendientes,
            SUM(CASE WHEN ec.nombre = 'Completado' THEN 1 ELSE 0 END) AS completadas,
            SUM(CASE WHEN ec.nombre = 'Cancelado'  THEN 1 ELSE 0 END) AS canceladas
        FROM CitaProgramada c
        LEFT JOIN EstadoCita ec ON c.id_EstadoCita = ec.idEstadoCita
        WHERE c.fechaEliminacion IS NULL
          AND MONTH(c.fecha) = MONTH(GETDATE())
          AND YEAR(c.fecha)  = YEAR(GETDATE())
        """, nativeQuery = true)
    Object[] getStatsMes();
}
