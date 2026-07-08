package com.utp.sistemaclinicaveterinaria.modulos.Receta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Receta.Projection.RecetaListarProjection;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    @Query(value = """
            SELECT idReceta, fechaReceta
            FROM Receta
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RecetaCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT idReceta, fechaReceta, fechaCreacion
            FROM Receta
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RecetaListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT r.idReceta, r.id_Consulta AS idConsulta, r.fechaReceta, r.id_EmpleadoAsociado AS idEmpleadoAsociado,
                   CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
                   r.fechaCreacion,
                   CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
                   r.fechaModificacion,
                   CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
                   r.fechaEliminacion
            FROM Receta AS r
            LEFT JOIN EmpleadoAsociado AS eac ON r.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON r.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON r.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE r.idReceta = :id AND r.id_Asociado = :idAsociado
            """, nativeQuery = true)
    RecetaDetalleProjection detalle(@Param("id") Integer id, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Receta
            SET fechaEliminacion = GETDATE(), id_EmpleadoEliminador = :idUsuario
            WHERE idReceta = :id AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(@Param("id") Integer id, @Param("idUsuario") Integer idUsuario, @Param("idAsociado") Integer idAsociado);

    Optional<Receta> findByIdConsultaAndFechaEliminacionIsNull(Integer idConsulta);
}
