package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.Projection.MetodoIngresoListarProjection;

public interface MetodoIngresoRepository extends JpaRepository<MetodoIngreso, Integer> {

    @Query(value = """
            SELECT
            idMetodoIngreso,
            nombre
            FROM MetodoIngreso
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<MetodoIngresoCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idMetodoIngreso,
            nombre,
            descripcion,
            estado
            FROM MetodoIngreso
            WHERE id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<MetodoIngresoListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idMetodoIngreso,
            nombre,
            descripcion,
            estado
            FROM MetodoIngreso
            WHERE idMetodoIngreso = :idMetodoIngreso AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    MetodoIngresoDetalleProjection detalle(@Param("idMetodoIngreso") Integer idMetodoIngreso, @Param("idAsociado") Integer idAsociado);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE MetodoIngreso
            SET
            estado = 0
            WHERE idMetodoIngreso = :idMetodoIngreso AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    void eliminar(@Param("idMetodoIngreso") Integer idMetodoIngreso, @Param("idAsociado") Integer idAsociado);
}
