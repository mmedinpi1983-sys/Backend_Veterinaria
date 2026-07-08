package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolesClinica.Projection.RolesClinicaListarProjection;

public interface RolesClinicaRepository extends JpaRepository<RolesClinica, Integer> {

    @Query(value = """
            SELECT
            idRoles,
            nombreRol
            FROM RolesClinica
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RolesClinicaCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idRoles,
            nombreRol,
            estado,
            fechaCreacion
            FROM RolesClinica
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RolesClinicaListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            r.idRoles,
            r.nombreRol,
            r.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
            r.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
            r.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
            r.fechaEliminacion
            FROM RolesClinica AS r
            LEFT JOIN EmpleadoAsociado AS eac ON r.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON r.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON r.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE r.idRoles = :idRoles AND r.id_Asociado = :idAsociado
            """, nativeQuery = true)
    RolesClinicaDetalleProjection detalle(@Param("idRoles") Integer idRoles, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE RolesClinica
            SET
            estado = 0,
            id_EmpleadoEliminador = :idUsuario,
            fechaEliminacion = GETDATE()
            WHERE idRoles = :idRoles AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idRoles") Integer idRoles,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
