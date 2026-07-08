package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection.AtencionVacunacionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion.Projection.AtencionVacunacionListarProjection;

public interface AtencionVacunacionRepository extends JpaRepository<AtencionVacunacion, Integer> {

    @Query(value = """
            SELECT
            av.idVacunacion,
            av.observaciones,
            av.fechaCreacion,
            av.estado
            FROM AtencionVacunacion AS av
            WHERE av.fechaEliminacion IS NULL
            ORDER BY av.fechaCreacion DESC
            """, nativeQuery = true)
    List<AtencionVacunacionListarProjection> listar();

    @Query(value = """
            SELECT
            av.idVacunacion,
            av.id_Atencion AS idAtencion,
            av.id_Vacuna AS idVacuna,
            av.dosis,
            av.fechaAplicacion,
            av.fechaRefuerzo,
            av.observaciones,
            av.estado,
            av.id_Consulta AS idConsulta,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            av.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            av.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            av.fechaEliminacion
            FROM AtencionVacunacion AS av
            LEFT JOIN EmpleadoAsociado AS eac ON av.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON av.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON av.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE av.idVacunacion = :idVacunacion
            """, nativeQuery = true)
    AtencionVacunacionDetalleProjection detalle(@Param("idVacunacion") Integer idVacunacion);

    @Query(value = """
            UPDATE AtencionVacunacion
            SET
            fechaEliminacion = GETDATE(),
            id_EmpleadoEliminador = :idUsuario
            WHERE idVacunacion = :idVacunacion
            """, nativeQuery = true)
    void eliminar(
            @Param("idVacunacion") Integer idVacunacion,
            @Param("idUsuario") Integer idUsuario);
}
