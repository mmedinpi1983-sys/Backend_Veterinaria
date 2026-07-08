package com.utp.sistemaclinicaveterinaria.modulos.Permiso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Permiso.Projection.PermisoListarProjection;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    @Query(value = """
            SELECT
            idPermiso,
            nombrePermiso
            FROM Permiso
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<PermisoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idPermiso,
            nombrePermiso,
            descripcionPermiso,
            estado,
            fechaCreacion
            FROM Permiso
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<PermisoListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idPermiso,
            nombrePermiso,
            descripcionPermiso,
            estado,
            fechaCreacion,
            fechaModificacion,
            fechaEliminacion
            FROM Permiso
            WHERE idPermiso = :idPermiso AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    PermisoDetalleProjection detalle(@Param("idPermiso") Integer idPermiso, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Permiso
            SET
            estado = 0,
            fechaEliminacion = GETDATE()
            WHERE idPermiso = :idPermiso AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idPermiso") Integer idPermiso,
            @Param("idAsociado") Integer idAsociado);
}
