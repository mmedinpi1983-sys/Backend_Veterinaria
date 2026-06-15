package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitaProgramadaRepository extends JpaRepository<CitaProgramada, Integer> {

    List<CitaProgramada> findByFechaEliminacionIsNull();

    Optional<CitaProgramada> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);

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

    // Veterinarios disponibles para agendar: uno por veterinario+turno, con su programación
    // (para enlazar la cita) y el horario del turno (para filtrar las horas en el modal).
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

    // Horas (HH:mm) ya ocupadas por citas NO canceladas de esa programación (veterinario) en una fecha.
    // excluirId permite ignorar una cita concreta (al reprogramar, para no chocar con su propio horario).
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