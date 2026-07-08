package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso.Projection.NivelPermisoListarProjection;

public interface NivelPermisoRepository extends JpaRepository<NivelPermiso, Integer> {

    @Query(value = """
            SELECT
            id_Nivel_Permiso AS idNivelPermiso,
            id_Permiso AS idPermiso,
            id_NivelSuscripcion AS idNivelSuscripcion,
            estado
            FROM Nivel_Permiso
            WHERE estado = 1
            """, nativeQuery = true)
    List<NivelPermisoCatalogoProjection> catalogo();

    @Query(value = """
            SELECT
            id_Nivel_Permiso AS idNivelPermiso,
            id_Permiso AS idPermiso,
            id_NivelSuscripcion AS idNivelSuscripcion,
            estado,
            fechaCreacion
            FROM Nivel_Permiso
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<NivelPermisoListarProjection> listar();

    @Query(value = """
            SELECT
            np.id_Nivel_Permiso AS idNivelPermiso,
            np.id_Permiso AS idPermiso,
            np.id_NivelSuscripcion AS idNivelSuscripcion,
            np.estado,
            sac.correo AS empleadoCreador,
            np.fechaCreacion,
            sam.correo AS empleadoModificador,
            np.fechaModificacion,
            sae.correo AS empleadoEliminador,
            np.fechaEliminacion
            FROM Nivel_Permiso AS np
            LEFT JOIN SuperAdmin AS sac ON np.id_SuperAdminCreador = sac.idSuper
            LEFT JOIN SuperAdmin AS sam ON np.id_SuperAdminModificador = sam.idSuper
            LEFT JOIN SuperAdmin AS sae ON np.id_SuperAdminEliminador = sae.idSuper
            WHERE np.id_Nivel_Permiso = :idNivelPermiso
            """, nativeQuery = true)
    NivelPermisoDetalleProjection detalle(@Param("idNivelPermiso") Integer idNivelPermiso);

    @Query(value = """
            UPDATE Nivel_Permiso
            SET
            fechaEliminacion = GETDATE(),
            id_SuperAdminEliminador = :idUsuario
            WHERE id_Nivel_Permiso = :idNivelPermiso
            """, nativeQuery = true)
    void eliminar(@Param("idNivelPermiso") Integer idNivelPermiso, @Param("idUsuario") Integer idUsuario);
}
