package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.EspecieRaza;

import jakarta.transaction.Transactional;

public interface EspecieRepository extends JpaRepository<EspecieRaza, Integer> {

        /// lista de catalogo
        @Query(value = """
                        SELECT
                        idEspecieRaza,
                        nombre
                        FROM EspecieRaza
                        WHERE
                        estado = 1 AND
                        id_Especie IS NULL AND
                        id_Asociado = :idAsociado
                        ORDER BY nombre
                        """, nativeQuery = true)
        List<Object[]> catalogo(@Param("idAsociado") Integer idAsociado);

        // lista para tabla
        @Query(value = """
                        SELECT
                        idEspecieRaza,
                        nombre,
                        estado,
                        fechaCreacion
                        FROM EspecieRaza
                        WHERE
                        (:nombre IS NULL OR nombre LIKE CONCAT('%', :nombre, '%')) AND
                        (:estado IS NULL OR estado = :estado) AND
                        id_Asociado = :idAsociado;
                        """, nativeQuery = true)
        List<Object[]> listar(
                        @Param("nombre") String nombre,
                        @Param("estado") Boolean estado,
                        @Param("idAsociado") Integer idAsociado);

        // Detalle
        @Query(value = """
                        SELECT
                        e.idEspecieRaza,
                        e.nombre,
                        e.estado,
                        CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
                        e.fechaCreacion,
                        CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
                        e.fechaModificacion,
                        CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
                        e.fechaEliminacion
                        FROM EspecieRaza AS e
                        LEFT JOIN EmpleadoAsociado AS eac ON e.id_EmpleadoCreador = eac.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eam ON e.id_EmpleadoModificador = eam.idEmpleadoAsociado
                        LEFT JOIN EmpleadoAsociado AS eae ON e.id_EmpleadoEliminador = eae.idEmpleadoAsociado
                        WHERE e.id_Especie IS NULL AND
                        (e.id_Asociado IS NULL OR e.id_Asociado = :idAsociado) AND
                        e.idEspecieRaza = :idEspecie
                        """, nativeQuery = true)
        Object[] detalle(
                        @Param("idEspecie") Integer idEspecie,
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
                        WHERE idEspecieRaza = :idEspecie AND id_Asociado = :idAsociado
                        """, nativeQuery = true)
        void eliminar(
                        @Param("idEspecie") Integer idEspecie,
                        @Param("idUsuario") Integer idUsuarioEliminador,
                        @Param("idAsociado") Integer idAsociado);
}