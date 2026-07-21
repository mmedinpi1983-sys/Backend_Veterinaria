package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Consultorio.Projection.ConsultorioListarProjection;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Integer> {

    @Query(value = """
            SELECT
            idConsultorio,
            nombre,
            piso
            FROM Consultorio
            WHERE estado = 1 AND fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            ORDER BY nombre
            """, nativeQuery = true)
    List<ConsultorioCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idConsultorio,
            nombre,
            descripcion,
            piso,
            estado,
            fechaCreacion
            FROM Consultorio
            WHERE id_Asociado = :idAsociado
              AND fechaEliminacion IS NULL
              AND estado = :estado
              AND nombre LIKE CONCAT('%',:nombre,'%')
            ORDER BY nombre
            """, nativeQuery = true)
    List<ConsultorioListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("nombre") String nombre);

    @Query(value = """
            SELECT
            c.idConsultorio,
            c.nombre,
            c.descripcion,
            c.piso,
            c.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            c.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            c.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            c.fechaEliminacion
            FROM Consultorio AS c
            LEFT JOIN EmpleadoAsociado AS eac ON c.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON c.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON c.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE c.idConsultorio = :idConsultorio AND c.id_Asociado = :idAsociado
            """, nativeQuery = true)
    ConsultorioDetalleProjection detalle(@Param("idConsultorio") Integer idConsultorio, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Consultorio
            SET estado = 0,
                id_EmpleadoEliminador = :idUsuario,
                fechaEliminacion = GETDATE()
            WHERE idConsultorio = :idConsultorio AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idConsultorio") Integer idConsultorio,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
