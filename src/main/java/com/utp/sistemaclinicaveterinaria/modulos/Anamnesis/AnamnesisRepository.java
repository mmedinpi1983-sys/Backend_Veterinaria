package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection.AnamnesisDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection.AnamnesisListarProjection;

public interface AnamnesisRepository extends JpaRepository<Anamnesis, Integer> {

    @Query(value = """
            SELECT
            a.idAnamnesis,
            a.id_Consulta AS idConsulta,
            a.antecedentes,
            a.medicamentosActuales,
            a.alimentacion,
            a.fechaCreacion
            FROM Anamnesis AS a
            WHERE a.fechaEliminacion IS NULL
            ORDER BY a.fechaCreacion DESC
            """, nativeQuery = true)
    List<AnamnesisListarProjection> listar();

    Optional<Anamnesis> findByIdConsultaAndFechaEliminacionIsNull(Integer idConsulta);

    @Query(value = """
            SELECT
            a.idAnamnesis,
            a.id_Consulta AS idConsulta,
            a.antecedentes,
            a.alergias,
            a.cirugiasAnteriores,
            a.medicamentosActuales,
            a.alimentacion,
            a.comportamiento,
            a.inicioSintomas,
            a.evolucionSintomas,
            a.observaciones,
            a.detalleAlergias,
            a.detalleCirugias,
            a.historialVacunacion,
            a.estiloVida,
            a.historialReproductivo,
            a.reproduccionDetalle,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            a.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            a.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            a.fechaEliminacion
            FROM Anamnesis AS a
            LEFT JOIN EmpleadoAsociado AS eac ON a.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON a.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON a.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE a.idAnamnesis = :idAnamnesis
            """, nativeQuery = true)
    AnamnesisDetalleProjection detalle(@Param("idAnamnesis") Integer idAnamnesis);

    @Query(value = """
            UPDATE Anamnesis
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idAnamnesis = :idAnamnesis
            """, nativeQuery = true)
    void eliminar(
            @Param("idAnamnesis") Integer idAnamnesis,
            @Param("idUsuario") Integer idUsuario);
}
