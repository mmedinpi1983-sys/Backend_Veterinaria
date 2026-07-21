package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection.AtencionEsteticaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica.Projection.AtencionEsteticaListarProjection;

public interface AtencionEsteticaRepository extends JpaRepository<AtencionEstetica, Integer> {

    @Query(value = """
            SELECT
            ae.idEstetica,
            ae.detalleServicio,
            ae.observaciones,
            ae.fechaCreacion,
            ae.estado
            FROM AtencionEstetica AS ae
            WHERE ae.fechaEliminacion IS NULL
            ORDER BY ae.fechaCreacion DESC
            """, nativeQuery = true)
    List<AtencionEsteticaListarProjection> listar();

    @Query(value = """
            SELECT
            ae.idEstetica,
            ae.id_Atencion AS idAtencion,
            ae.id_Servicio AS idServicio,
            ae.detalleServicio,
            ae.observaciones,
            ae.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            ae.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            ae.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            ae.fechaEliminacion
            FROM AtencionEstetica AS ae
            LEFT JOIN EmpleadoAsociado AS eac ON ae.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON ae.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON ae.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE ae.idEstetica = :idEstetica
            """, nativeQuery = true)
    AtencionEsteticaDetalleProjection detalle(@Param("idEstetica") Integer idEstetica);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE AtencionEstetica
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idEstetica = :idEstetica
            """, nativeQuery = true)
    void eliminar(
            @Param("idEstetica") Integer idEstetica,
            @Param("idUsuario") Integer idUsuario);
}
