package com.utp.sistemaclinicaveterinaria.modulos.Dueno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection.DuenoListarProjection;

public interface DuenoRepository extends JpaRepository<Dueno, Integer> {

    boolean existsByNroDocumentoAndFechaEliminacionIsNull(String nroDocumento);

    boolean existsByNroDocumentoAndIdDuenoNotAndFechaEliminacionIsNull(String nroDocumento, Integer idDueno);

    @Query(value = """
            SELECT
            idDueno,
            nombre,
            apellidoPaterno,
            apellidoMaterno,
            nroDocumento
            FROM Dueno
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<DuenoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idDueno,
            nombre,
            apellidoPaterno,
            apellidoMaterno,
            nroDocumento,
            estado,
            fechaCreacion
            FROM Dueno
            WHERE id_Asociado = :idAsociado AND estado = :estado
            AND (nombre LIKE CONCAT('%',:q,'%') OR apellidoPaterno LIKE CONCAT('%',:q,'%') OR nroDocumento LIKE CONCAT('%',:q,'%'))
            """, nativeQuery = true)
    List<DuenoListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("q") String q);

    @Query(value = """
            SELECT
            d.idDueno,
            d.id_DocumentoIdentidad AS idDocumentoIdentidad,
            d.nombre,
            d.apellidoPaterno,
            d.apellidoMaterno,
            d.nroDocumento,
            d.nroTelefono,
            d.correoElectronico,
            d.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            d.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            d.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            d.fechaEliminacion
            FROM Dueno AS d
            LEFT JOIN EmpleadoAsociado AS eac ON d.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON d.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON d.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE d.idDueno = :idDueno AND d.id_Asociado = :idAsociado
            """, nativeQuery = true)
    DuenoDetalleProjection detalle(@Param("idDueno") Integer idDueno, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Dueno
            SET estado = 0, id_EmpleadoEliminador = :idUsuario, fechaEliminacion = GETDATE()
            WHERE idDueno = :idDueno AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idDueno") Integer idDueno,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
