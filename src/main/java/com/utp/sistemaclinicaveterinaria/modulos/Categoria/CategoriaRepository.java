package com.utp.sistemaclinicaveterinaria.modulos.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.Projection.CategoriaListarProjection;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = """
            SELECT
            idCategoria,
            nombreCategoria
            from Categoria
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<CategoriaCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idCategoria,
            nombreCategoria,
            estado,
            fechaCreacion
            FROM Categoria
            WHERE id_Asociado = :idAsociado AND estado = :estado AND nombreCategoria LIKE CONCAT('%',:nombreCategoria,'%')
            """, nativeQuery = true)
    List<CategoriaListarProjection> listar(
            @Param("idAsociado") Integer idAsociado,
            @Param("estado") Boolean estado,
            @Param("nombreCategoria") String nombreCategoria);

    @Query(value = """
            SELECT
            c.idCategoria,
            c.nombreCategoria,
            c.estado,
            CONCAT(TRIM(eac.apellidoPaterno),' ', TRIM(eac.apellidoMaterno), ' ', TRIM(eac.nombreEmpleado))AS empleadoCreador,
            c.fechaCreacion,
            CONCAT(TRIM(eam.apellidoPaterno),' ', TRIM(eam.apellidoMaterno), ' ', TRIM(eam.nombreEmpleado))AS empleadoModificador,
            c.fechaModificacion,
            CONCAT(TRIM(eae.apellidoPaterno),' ', TRIM(eae.apellidoMaterno), ' ', TRIM(eae.nombreEmpleado))AS empleadoEliminador,
            c.fechaEliminacion
            FROM Categoria AS c
            LEFT JOIN EmpleadoAsociado AS eac ON c.id_EmpleadoCreador = eac.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eam ON c.id_EmpleadoModificador = eam.idEmpleadoAsociado
            LEFT JOIN EmpleadoAsociado AS eae ON c.id_EmpleadoEliminador = eae.idEmpleadoAsociado
            WHERE c.idCategoria = :idCategoria AND c.id_Asociado = :idAsociado
            """, nativeQuery = true)
    CategoriaDetalleProjection detalle(@Param("idCategoria") Integer idCategoria, @Param("idAsociado") Integer idAsociado);

    @Query(value = """
            UPDATE Categoria
            SET
            estado = 0,
            id_EmpleadoEliminador = :idUsuario,
            fechaEliminacion = GETDATE()
            WHERE idCategoria = :idCategoria AND id_Asociado = :idAsociado
                        """, nativeQuery = true)
    void eliminar(
            @Param("idCategoria") Integer idCategoria,
            @Param("idUsuario") Integer idUsuario,
            @Param("idAsociado") Integer idAsociado);
}
