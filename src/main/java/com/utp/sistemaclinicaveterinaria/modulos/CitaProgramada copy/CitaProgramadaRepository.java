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
