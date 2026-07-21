package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection.RecetaDetalleListarProjection;

public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Integer> {

    @Query(value = """
            SELECT idRecetaDetalle, dosis, frecuencia
            FROM RecetaDetalle
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<RecetaDetalleCatalogoProjection> catalogo();

    @Query(value = """
            SELECT idRecetaDetalle, dosis, frecuencia, duracion, fechaCreacion
            FROM RecetaDetalle
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<RecetaDetalleListarProjection> listar();

    @Query(value = """
            SELECT rd.idRecetaDetalle, rd.id_Receta AS idReceta, rd.id_Medicamento AS idMedicamento,
                   rd.dosis, rd.frecuencia, rd.duracion, rd.viaAdministracion, rd.indicacionesEspecificas,
                   CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
                   rd.fechaCreacion,
                   CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
                   rd.fechaModificacion,
                   CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
                   rd.fechaEliminacion
            FROM RecetaDetalle AS rd
            LEFT JOIN EmpleadoAsociado AS eac ON rd.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON rd.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON rd.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE rd.idRecetaDetalle = :id
            """, nativeQuery = true)
    RecetaDetalleDetalleProjection detalle(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE RecetaDetalle
            SET fechaEliminacion = GETDATE(), id_EmpleadoEliminador = :idUsuario
            WHERE idRecetaDetalle = :id
            """, nativeQuery = true)
    void eliminar(@Param("id") Integer id, @Param("idUsuario") Integer idUsuario);

    List<RecetaDetalle> findByIdRecetaAndFechaEliminacionIsNull(Integer idReceta);
}
