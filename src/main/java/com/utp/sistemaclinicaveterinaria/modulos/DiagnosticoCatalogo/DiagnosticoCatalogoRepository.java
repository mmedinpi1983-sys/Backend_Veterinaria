package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.Projection.DiagnosticoCatalogoListarProjection;

public interface DiagnosticoCatalogoRepository extends JpaRepository<DiagnosticoCatalogo, Integer> {

    @Query(value = """
            SELECT
            idDiagnosticoCatalogo,
            codigoDiagnostico,
            nombreDiagnostico
            FROM DiagnosticoCatalogo
            """, nativeQuery = true)
    List<DiagnosticoCatalogoCatalogoProjection> catalogo();

    @Query(value = """
            SELECT
            idDiagnosticoCatalogo,
            codigoDiagnostico,
            nombreDiagnostico,
            descripcion
            FROM DiagnosticoCatalogo
            """, nativeQuery = true)
    List<DiagnosticoCatalogoListarProjection> listar();

    @Query(value = """
            SELECT
            idDiagnosticoCatalogo,
            codigoDiagnostico,
            nombreDiagnostico,
            descripcion
            FROM DiagnosticoCatalogo
            WHERE idDiagnosticoCatalogo = :idDiagnosticoCatalogo
            """, nativeQuery = true)
    DiagnosticoCatalogoDetalleProjection detalle(@Param("idDiagnosticoCatalogo") Integer idDiagnosticoCatalogo);
}
