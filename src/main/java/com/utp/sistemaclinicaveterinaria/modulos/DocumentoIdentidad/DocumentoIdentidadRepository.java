package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad.Projection.DocumentoIdentidadListarProjection;

public interface DocumentoIdentidadRepository extends JpaRepository<DocumentoIdentidad, Integer> {

    @Query(value = """
            SELECT
            idDocumentoIdentidad,
            descripcion
            FROM DocumentoIdentidad
            WHERE estado = 1 AND id_Asociado = :idAsociado
            """, nativeQuery = true)
    List<DocumentoIdentidadCatalogoProjection> catalogo(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idDocumentoIdentidad,
            descripcion,
            estado
            FROM DocumentoIdentidad
            WHERE id_Asociado = :idAsociado
            ORDER BY descripcion
            """, nativeQuery = true)
    List<DocumentoIdentidadListarProjection> listar(@Param("idAsociado") Integer idAsociado);

    @Query(value = """
            SELECT
            idDocumentoIdentidad,
            descripcion,
            estado
            FROM DocumentoIdentidad
            WHERE idDocumentoIdentidad = :idDocumentoIdentidad
            """, nativeQuery = true)
    DocumentoIdentidadDetalleProjection detalle(@Param("idDocumentoIdentidad") Integer idDocumentoIdentidad);

    @Query(value = """
            DELETE FROM DocumentoIdentidad
            WHERE idDocumentoIdentidad = :idDocumentoIdentidad
            """, nativeQuery = true)
    void eliminar(@Param("idDocumentoIdentidad") Integer idDocumentoIdentidad);
}
