package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion.Projection.EstadoSalidaAtencionListarProjection;

public interface EstadoSalidaAtencionRepository extends JpaRepository<EstadoSalidaAtencion, Integer> {

    @Query(value = """
            SELECT idEstadoSalida, nombre
            FROM EstadoSalidaAtencion
            """, nativeQuery = true)
    List<EstadoSalidaAtencionCatalogoProjection> catalogo();

    @Query(value = """
            SELECT idEstadoSalida, nombre, descripcion
            FROM EstadoSalidaAtencion
            WHERE nombre LIKE CONCAT('%',:nombre,'%')
            """, nativeQuery = true)
    List<EstadoSalidaAtencionListarProjection> listar(@Param("nombre") String nombre);

    @Query(value = """
            SELECT idEstadoSalida, nombre, descripcion
            FROM EstadoSalidaAtencion
            WHERE idEstadoSalida = :idEstadoSalida
            """, nativeQuery = true)
    EstadoSalidaAtencionDetalleProjection detalle(@Param("idEstadoSalida") Integer idEstadoSalida);

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM EstadoSalidaAtencion
            WHERE idEstadoSalida = :idEstadoSalida
            """, nativeQuery = true)
    void eliminar(@Param("idEstadoSalida") Integer idEstadoSalida);
}
