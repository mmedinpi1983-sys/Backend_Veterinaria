package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;

import jakarta.transaction.Transactional;

public interface RazaRepository extends JpaRepository<EspecieRaza, Integer> {

        // Lista de catalogo
        @Query(value = """
                        SELECT
                        idEspecieRaza,
                        nombre,
                        id_Especie
                        FROM EspecieRaza
                        WHERE id_Asociado = :idAsociado AND
                        id_Especie IS NOT NULL AND
                        estado = 1
                        ORDER BY nombre ASC
                        """, nativeQuery = true)
        List<Object[]> catalogo(@Param("idAsociado") Integer idAsociado);

        // lista para tabla
        @Query(value = """
                        SELECT
                        raza.idEspecieRaza,
                        raza.nombre,
                        especie.nombre,
                        raza.estado,
                        raza.fechaCreacion
                        FROM EspecieRaza AS raza
                        LEFT JOIN EspecieRaza AS especie ON raza.id_Especie = especie.idEspecieRaza
                        WHERE
                        (:nombre IS NULL OR raza.nombre LIKE CONCAT('%', :nombre, '%')) AND
                        (:estado IS NULL OR raza.estado = :estado) AND
                        raza.id_Asociado = :idAsociado
                        ORDER BY raza.nombre ASC
                        """, nativeQuery = true)
        List<Object[]> listar(
                        @Param("nombre") String nombre,
                        @Param("estado") Boolean estado,
                        @Param("idEspecie") Integer idEspecie,
                        @Param("idAsociado") Integer idAsociado);

        // Detalle
        @Query(value = """
                        SELECT
                        raza.idEspecieRaza,
                        raza.nombre,
                        raza.id_Especie,
                        especie.nombre,
                        raza.estado,
                        CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
                        raza.fechaCreacion,
                        CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
                        raza.fechaModificacion,
                        CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
                        raza.fechaEliminacion
                        FROM EspecieRaza AS raza
                        LEFT JOIN EspecieRaza AS especie ON raza.id_Especie = especie.idEspecieRaza
                        LEFT JOIN EmpleadoAsociado AS eac ON raza.id_EmpleadoCreador = eac.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eam ON raza.id_EmpleadoModificador = eam.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eae ON raza.id_EmpleadoEliminador = eae.idEmpleadoAsociado
                        WHERE (raza.id_Asociado IS NULL OR raza.id_Asociado = :idAsociado) AND raza.idEspecieRaza = :idRaza
                        """, nativeQuery = true)
        Object[] detalle(
                        @Param("idRaza") Integer idRaza,
                        @Param("idAsociado") Integer idAsociado);

        // eliminar
        @Modifying
        @Transactional
        @Query(value = """
                        UPDATE EspecieRaza
                        SET
                        estado = 0,
                        id_EmpleadoEliminador = :idUsuario,
                        fechaEliminacion = GETDATE()
                        WHERE idEspecieRaza = :idRaza AND id_Asociado = :idAsociado
                        """, nativeQuery = true)
        void eliminar(
                        @Param("idRaza") Integer idRaza,
                        @Param("idUsuario") Integer idUsuarioEliminador,
                        @Param("idAsociado") Integer idAsociado);
}
