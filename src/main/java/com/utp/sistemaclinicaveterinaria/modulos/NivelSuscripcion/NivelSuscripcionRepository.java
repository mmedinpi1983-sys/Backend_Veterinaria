package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion.Projection.NivelSuscripcionListarProjection;

public interface NivelSuscripcionRepository extends JpaRepository<NivelSuscripcion, Integer> {

    @Query(value = """
            SELECT
            idNivel,
            nombre
            FROM NivelSuscripcion
            WHERE estado = 1
            """, nativeQuery = true)
    List<NivelSuscripcionCatalogoProjection> catalogo();

    @Query(value = """
            SELECT
            idNivel,
            nombre,
            cantidadUsuario,
            precioMensual,
            precioAnual,
            estado,
            fechaCreacion
            FROM NivelSuscripcion
            WHERE fechaEliminacion IS NULL
            """, nativeQuery = true)
    List<NivelSuscripcionListarProjection> listar();

    @Query(value = """
            SELECT
            ns.idNivel,
            ns.nombre,
            ns.cantidadUsuario,
            ns.precioMensual,
            ns.precioAnual,
            ns.estado,
            sac.correo AS empleadoCreador,
            ns.fechaCreacion,
            sam.correo AS empleadoModificador,
            ns.fechaModificacion,
            sae.correo AS empleadoEliminador,
            ns.fechaEliminacion
            FROM NivelSuscripcion AS ns
            LEFT JOIN SuperAdmin AS sac ON ns.id_SuperAdminCreador = sac.idSuper
            LEFT JOIN SuperAdmin AS sam ON ns.id_SuperAdminModificador = sam.idSuper
            LEFT JOIN SuperAdmin AS sae ON ns.id_SuperAdminEliminador = sae.idSuper
            WHERE ns.idNivel = :idNivel
            """, nativeQuery = true)
    NivelSuscripcionDetalleProjection detalle(@Param("idNivel") Integer idNivel);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE NivelSuscripcion
            SET
            fechaEliminacion = GETDATE(),
            id_SuperAdminEliminador = :idUsuario
            WHERE idNivel = :idNivel
            """, nativeQuery = true)
    void eliminar(@Param("idNivel") Integer idNivel, @Param("idUsuario") Integer idUsuario);
}
