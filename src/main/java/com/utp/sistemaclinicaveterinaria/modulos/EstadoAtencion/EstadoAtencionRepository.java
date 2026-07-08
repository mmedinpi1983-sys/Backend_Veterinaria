package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion.Projection.EstadoAtencionListarProjection;

public interface EstadoAtencionRepository extends JpaRepository<EstadoAtencion, Integer> {

    @Query(value = """
            SELECT idEstadoAtencion, nombre
            FROM EstadoAtencion
            """, nativeQuery = true)
    List<EstadoAtencionCatalogoProjection> catalogo();

    @Query(value = """
            SELECT idEstadoAtencion, nombre
            FROM EstadoAtencion
            WHERE nombre LIKE CONCAT('%',:nombre,'%')
            """, nativeQuery = true)
    List<EstadoAtencionListarProjection> listar(@Param("nombre") String nombre);

    @Query(value = """
            SELECT idEstadoAtencion, nombre
            FROM EstadoAtencion
            WHERE idEstadoAtencion = :idEstadoAtencion
            """, nativeQuery = true)
    EstadoAtencionDetalleProjection detalle(@Param("idEstadoAtencion") Integer idEstadoAtencion);

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM EstadoAtencion
            WHERE idEstadoAtencion = :idEstadoAtencion
            """, nativeQuery = true)
    void eliminar(@Param("idEstadoAtencion") Integer idEstadoAtencion);
}
