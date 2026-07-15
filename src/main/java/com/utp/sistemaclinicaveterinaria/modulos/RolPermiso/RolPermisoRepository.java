package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.RolPermiso.Projection.RolPermisoListarProjection;

public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {

    @Query(value = """
            SELECT
            id_Rol_Permiso AS idRolPermiso,
            id_RolesClinica AS idRolesClinica,
            id_Permiso AS idPermiso
            FROM Rol_Permiso
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RolPermisoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            id_Rol_Permiso AS idRolPermiso,
            id_RolesClinica AS idRolesClinica,
            id_Permiso AS idPermiso,
            fechaCreacion
            FROM Rol_Permiso
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<RolPermisoListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            rp.id_Rol_Permiso AS idRolPermiso,
            rp.id_RolesClinica AS idRolesClinica,
            rp.id_Permiso AS idPermiso,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            rp.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            rp.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            rp.fechaEliminacion
            FROM Rol_Permiso AS rp
            LEFT JOIN EmpleadoAsociado AS eac ON rp.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON rp.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON rp.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE rp.id_Rol_Permiso = :idRolPermiso AND rp.id_Asociado = :idAsociado
            """, nativeQuery = true)
    RolPermisoDetalleProjection detalle(@Param("idRolPermiso") Integer idRolPermiso, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT p.nombrePermiso
            FROM Rol_Permiso rp
            JOIN Permiso p ON p.idPermiso = rp.id_Permiso
            WHERE rp.id_RolesClinica = :idRolesClinica
              AND rp.id_Asociado = :idAsociado
              AND rp.fechaEliminacion IS NULL
              AND p.estado = 1
            """, nativeQuery = true)
    List<String> nombrePermisosDeRol(@Param("idRolesClinica") Integer idRolesClinica, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Rol_Permiso
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE id_Rol_Permiso = :idRolPermiso AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idRolPermiso") Integer idRolPermiso,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
