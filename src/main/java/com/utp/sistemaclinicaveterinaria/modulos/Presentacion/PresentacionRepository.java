package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Presentacion.Projection.PresentacionListarProjection;

public interface PresentacionRepository extends JpaRepository<Presentacion, Integer> {

    @Query(value = """
            SELECT
            idPresentacion,
            nombre
            FROM Presentacion
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<PresentacionCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idPresentacion,
            nombre,
            estado,
            fechaCreacion
            FROM Presentacion
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<PresentacionListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            p.idPresentacion,
            p.nombre,
            p.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
            p.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
            p.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
            p.fechaEliminacion
            FROM Presentacion AS p
            LEFT JOIN EmpleadoAsociado AS eac ON p.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON p.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON p.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE p.idPresentacion = :idPresentacion AND p.id_Asociado = :idAsociado
            """, nativeQuery = true)
    PresentacionDetalleProjection detalle(@Param("idPresentacion") Integer idPresentacion, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Presentacion
            SET
            estado = 0,
            id_EmpleadoEliminador = :idUsuario,
            fechaEliminacion = GETDATE()
            WHERE idPresentacion = :idPresentacion AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idPresentacion") Integer idPresentacion,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
