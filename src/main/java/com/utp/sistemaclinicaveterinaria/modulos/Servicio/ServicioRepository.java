package com.utp.sistemaclinicaveterinaria.modulos.Servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Servicio.Projection.ServicioListarProjection;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query(value = """
            SELECT
            idServicio,
            nombre
            FROM Servicio
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<ServicioCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idServicio,
            nombre,
            estado,
            fechaCreacion
            FROM Servicio
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<ServicioListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            s.idServicio,
            s.nombre,
            s.id_Categoria,
            s.precio,
            s.requiereTriaje,
            s.estado,
            s.duracionEstimada,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
            s.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
            s.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
            s.fechaEliminacion
            FROM Servicio AS s
            LEFT JOIN EmpleadoAsociado AS eac ON s.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON s.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON s.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE s.idServicio = :idServicio AND s.id_Asociado = :idAsociado
            """, nativeQuery = true)
    ServicioDetalleProjection detalle(@Param("idServicio") Integer idServicio, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Servicio
            SET
            estado = 0,
            id_EmpleadoEliminador = :idUsuario,
            fechaEliminacion = GETDATE()
            WHERE idServicio = :idServicio AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idServicio") Integer idServicio,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
