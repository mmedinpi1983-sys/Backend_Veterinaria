package com.utp.sistemaclinicaveterinaria.modulos.Mascota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection.MascotaListarProjection;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    @Query(value = """
            SELECT
            idMascota,
            nombre,
            sexo,
            tamanio
            FROM Mascota
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<MascotaCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idMascota,
            nombre,
            sexo,
            tamanio,
            estado,
            fechaCreacion
            FROM Mascota
            WHERE id_Asociado = :idAsociado AND estado = :estado
            AND (nombre LIKE CONCAT('%',:q,'%'))
            """, nativeQuery = true)
    List<MascotaListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("q") String q);

    @Query(value = """
            SELECT
            m.idMascota,
            m.nombre,
            m.id_Especie AS idEspecie,
            m.id_Raza AS idRaza,
            m.fechaNacimiento,
            m.sexo,
            m.tamanio,
            m.notas,
            m.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            m.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            m.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            m.fechaEliminacion
            FROM Mascota AS m
            LEFT JOIN EmpleadoAsociado AS eac ON m.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON m.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON m.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE m.idMascota = :idMascota AND m.id_Asociado = :idAsociado
            """, nativeQuery = true)
    MascotaDetalleProjection detalle(@Param("idMascota") Integer idMascota, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Mascota
            SET estado = 0, id_EmpleadoEliminador = :idUsuario, fechaEliminacion = GETDATE()
            WHERE idMascota = :idMascota AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idMascota") Integer idMascota,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
                m.idMascota,
                m.nombre,
                COALESCE(er_esp.nombre, '') AS especie,
                COALESCE(er_raza.nombre, '') AS raza,
                dm.id_Dueno AS idDueno,
                ISNULL(d.nombre,'') + ' ' + ISNULL(d.apellidoPaterno,'') + ' ' + ISNULL(d.apellidoMaterno,'') AS nombreDueno,
                m.tamanio,
                m.sexo
            FROM Mascota m
            LEFT JOIN EspecieRaza er_esp ON m.id_Especie = er_esp.idEspecieRaza
            LEFT JOIN EspecieRaza er_raza ON m.id_Raza = er_raza.idEspecieRaza
            LEFT JOIN Dueno_Mascota dm ON m.idMascota = dm.id_Mascota AND dm.fechaEliminacion IS NULL
            LEFT JOIN Dueno d ON dm.id_Dueno = d.idDueno AND d.fechaEliminacion IS NULL
            WHERE m.fechaEliminacion IS NULL
            AND (:q IS NULL OR m.nombre LIKE '%' + :q + '%' OR d.nombre LIKE '%' + :q + '%' OR d.apellidoPaterno LIKE '%' + :q + '%')
            ORDER BY m.nombre ASC
            """, nativeQuery = true)
    List<MascotaSearchView> buscarConDueno(@Param("q") String q);
}
