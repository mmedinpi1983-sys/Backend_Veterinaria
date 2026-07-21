package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection.AtencionConsultaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta.Projection.AtencionConsultaListarProjection;

public interface AtencionConsultaRepository extends JpaRepository<AtencionConsulta, Integer> {

    @Query(value = """
            SELECT
            ac.idConsulta,
            ac.evaluacionClinica,
            ac.tratamiento,
            ac.indicaciones,
            ac.fechaCreacion
            FROM AtencionConsulta AS ac
            WHERE ac.fechaEliminacion IS NULL
            ORDER BY ac.fechaCreacion DESC
            """, nativeQuery = true)
    List<AtencionConsultaListarProjection> listar();

    Optional<AtencionConsulta> findByIdAtencionAndFechaEliminacionIsNull(Integer idAtencion);

    @Query(value = """
            SELECT
            ac.idConsulta,
            ac.id_Atencion AS idAtencion,
            ac.evaluacionClinica,
            ac.tratamiento,
            ac.indicaciones,
            ac.observaciones,
            ac.requiereControl,
            ac.fechaProximoControl,
            ac.motivoConsulta,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            ac.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            ac.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            ac.fechaEliminacion
            FROM AtencionConsulta AS ac
            LEFT JOIN EmpleadoAsociado AS eac ON ac.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON ac.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON ac.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE ac.idConsulta = :idConsulta
            """, nativeQuery = true)
    AtencionConsultaDetalleProjection detalle(@Param("idConsulta") Integer idConsulta);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE AtencionConsulta
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idConsulta = :idConsulta
            """, nativeQuery = true)
    void eliminar(
            @Param("idConsulta") Integer idConsulta,
            @Param("idUsuario") Integer idUsuario);
}
