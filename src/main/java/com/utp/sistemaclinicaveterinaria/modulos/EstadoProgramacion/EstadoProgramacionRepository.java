package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion.Projection.EstadoProgramacionListarProjection;

public interface EstadoProgramacionRepository extends JpaRepository<EstadoProgramacion, Integer> {

    @Query(value = """
            SELECT idEstadoProgramacion, nombre
            FROM EstadoProgramacion
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<EstadoProgramacionCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idEstadoProgramacion,
            nombre,
            descripcion,
            estado,
            fechaCreacion
            FROM EstadoProgramacion
            WHERE id_Asociado = :idAsociado AND estado = :estado AND nombre LIKE CONCAT('%',:nombre,'%')
            """, nativeQuery = true)
    List<EstadoProgramacionListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("nombre") String nombre);

    @Query(value = """
            SELECT
            ep.idEstadoProgramacion,
            ep.nombre,
            ep.descripcion,
            ep.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            ep.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            ep.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            ep.fechaEliminacion
            FROM EstadoProgramacion AS ep
            LEFT JOIN EmpleadoAsociado AS eac ON ep.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON ep.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON ep.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE ep.idEstadoProgramacion = :idEstadoProgramacion AND ep.id_Asociado = :idAsociado
            """, nativeQuery = true)
    EstadoProgramacionDetalleProjection detalle(@Param("idEstadoProgramacion") Integer idEstadoProgramacion, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE EstadoProgramacion
            SET
            estado = 0,
            id_EmpleadoEliminador = :idUsuario,
            fechaEliminacion = GETDATE()
            WHERE idEstadoProgramacion = :idEstadoProgramacion AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idEstadoProgramacion") Integer idEstadoProgramacion,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
