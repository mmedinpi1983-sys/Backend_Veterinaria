package com.utp.sistemaclinicaveterinaria.modulos.Asociado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection.AsociadoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection.AsociadoListarProjection;

public interface AsociadoRepository extends JpaRepository<Asociado, Integer> {

    @Query(value = """
            SELECT
            a.idAsociado,
            a.nombre,
            a.ruc,
            a.nombreDueno,
            a.fechaCreacion,
            a.activo
            FROM Asociado AS a
            WHERE a.fechaEliminacion IS NULL
            ORDER BY a.fechaCreacion DESC
            """, nativeQuery = true)
    List<AsociadoListarProjection> listar();

    @Query(value = """
            SELECT
            a.idAsociado,
            a.nombre,
            a.ruc,
            a.nombreDueno,
            a.apellidoDueno,
            a.id_NivelSuscripcion AS idNivelSuscripcion,
            a.activo,
            a.correoElectronico,
            a.nroTelefono,
            a.direccion,
            a.diasAtencion,
            sac.correo AS superAdminCreador,
            a.fechaCreacion,
            sam.correo AS superAdminModificador,
            a.fechaModificacion,
            sae.correo AS superAdminEliminador,
            a.fechaEliminacion
            FROM Asociado AS a
            LEFT JOIN SuperAdmin AS sac ON a.id_SuperAdminCreador = sac.idSuper
            LEFT JOIN SuperAdmin AS sam ON a.id_SuperAdminModificador = sam.idSuper
            LEFT JOIN SuperAdmin AS sae ON a.id_SuperAdminEliminador = sae.idSuper
            WHERE a.idAsociado = :idAsociado
            """, nativeQuery = true)
    AsociadoDetalleProjection detalle(@Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Asociado
            SET
            fechaEliminacion = GETDATE()
            WHERE idAsociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(@Param("idAsociado") Integer idAsociado);
}
