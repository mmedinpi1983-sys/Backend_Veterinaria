package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.EstadoCita.Projection.EstadoCitaListarProjection;

public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Integer> {

    @Query(value = """
            SELECT idEstadoCita, nombre
            FROM EstadoCita
            """, nativeQuery = true)
    List<EstadoCitaCatalogoProjection> catalogo();

    @Query(value = """
            SELECT idEstadoCita, nombre, descripcion
            FROM EstadoCita
            WHERE nombre LIKE CONCAT('%',:nombre,'%')
            """, nativeQuery = true)
    List<EstadoCitaListarProjection> listar(@Param("nombre") String nombre);

    @Query(value = """
            SELECT idEstadoCita, nombre, descripcion
            FROM EstadoCita
            WHERE idEstadoCita = :idEstadoCita
            """, nativeQuery = true)
    EstadoCitaDetalleProjection detalle(@Param("idEstadoCita") Integer idEstadoCita);

    @Modifying
    @Transactional
    @Query(value = """
            DELETE FROM EstadoCita
            WHERE idEstadoCita = :idEstadoCita
            """, nativeQuery = true)
    void eliminar(@Param("idEstadoCita") Integer idEstadoCita);
}
