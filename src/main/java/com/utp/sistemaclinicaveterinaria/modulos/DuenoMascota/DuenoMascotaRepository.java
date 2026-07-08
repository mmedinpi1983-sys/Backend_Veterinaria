package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection.DuenoMascotaListarProjection;

public interface DuenoMascotaRepository extends JpaRepository<DuenoMascota, Integer> {

    @Query(value = """
            SELECT
            idDuenoMascota,
            id_Dueno AS idDueno,
            id_Mascota AS idMascota
            FROM Dueno_Mascota
            WHERE fechaEliminacion IS NULL AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<DuenoMascotaCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idDuenoMascota,
            id_Dueno AS idDueno,
            id_Mascota AS idMascota,
            fechaCreacion
            FROM Dueno_Mascota
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<DuenoMascotaListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            dm.idDuenoMascota,
            dm.id_Dueno AS idDueno,
            dm.id_Mascota AS idMascota,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado)) AS empleadoCreador,
            dm.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado)) AS empleadoModificador,
            dm.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado)) AS empleadoEliminador,
            dm.fechaEliminacion
            FROM Dueno_Mascota AS dm
            LEFT JOIN EmpleadoAsociado AS eac ON dm.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON dm.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON dm.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE dm.idDuenoMascota = :idDuenoMascota AND dm.id_Asociado = :idAsociado
            """, nativeQuery = true)
    DuenoMascotaDetalleProjection detalle(@Param("idDuenoMascota") Integer idDuenoMascota, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Dueno_Mascota
            SET id_EmpleadoEliminador = :idUsuario, fechaEliminacion = GETDATE()
            WHERE idDuenoMascota = :idDuenoMascota AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(
            @Param("idDuenoMascota") Integer idDuenoMascota,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
