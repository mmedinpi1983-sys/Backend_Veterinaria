package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado.Projection.EmpleadoAsociadoListarProjection;

public interface EmpleadoAsociadoRepository extends JpaRepository<EmpleadoAsociado, Integer> {

    List<EmpleadoAsociado> findByFechaEliminacionIsNull();
    Optional<EmpleadoAsociado> findByIdEmpleadoAsociadoAndFechaEliminacionIsNull(Integer idEmpleadoAsociado);

    @Query(value = """
            SELECT
            idEmpleadoAsociado,
            correo,
            nombreEmpleado,
            apellidoPaterno,
            apellidoMaterno
            FROM EmpleadoAsociado
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<EmpleadoAsociadoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idEmpleadoAsociado,
            correo,
            nombreEmpleado,
            apellidoPaterno,
            apellidoMaterno,
            nroDocumento,
            id_RolesClinica AS idRolesClinica,
            estado,
            fechaCreacion
            FROM EmpleadoAsociado
            WHERE id_Asociado = :idAsociado AND fechaEliminacion IS NULL AND (:estado IS NULL OR estado = :estado)
            AND (nombreEmpleado LIKE CONCAT('%',:q,'%') OR apellidoPaterno LIKE CONCAT('%',:q,'%') OR nroDocumento LIKE CONCAT('%',:q,'%'))
            """, nativeQuery = true)
    List<EmpleadoAsociadoListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("q") String q);

    @Query(value = """
            SELECT
            ea.idEmpleadoAsociado,
            ea.id_Asociado AS idAsociado,
            ea.correo,
            ea.nombreEmpleado,
            ea.apellidoPaterno,
            ea.apellidoMaterno,
            ea.nroDocumento,
            ea.fechaNacimiento,
            ea.id_DocumentoIdentidad AS idDocumentoIdentidad,
            ea.id_RolesClinica AS idRolesClinica,
            ea.correoElectronico,
            ea.nroTelefono,
            ea.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            ea.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            ea.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            ea.fechaEliminacion
            FROM EmpleadoAsociado AS ea
            LEFT JOIN EmpleadoAsociado AS eac ON ea.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON ea.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON ea.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE ea.idEmpleadoAsociado = :idEmpleadoAsociado AND ea.id_Asociado = :idAsociado
            """, nativeQuery = true)
    EmpleadoAsociadoDetalleProjection detalle(@Param("idEmpleadoAsociado") Integer idEmpleadoAsociado, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE EmpleadoAsociado
            SET estado = 0, id_EmpleadoEliminador = :idUsuario, fechaEliminacion = GETDATE()
            WHERE idEmpleadoAsociado = :idEmpleadoAsociado AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idEmpleadoAsociado") Integer idEmpleadoAsociado,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
