package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoCatalogoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo.Projection.MedicamentoCatalogoListarProjection;

public interface MedicamentoCatalogoRepository extends JpaRepository<MedicamentoCatalogo, Integer> {

    @Query(value = """
            SELECT
            idMedicamento,
            codigoMedicamento,
            nombreMedicamento
            FROM MedicamentoCatalogo
            """, nativeQuery = true)
    List<MedicamentoCatalogoCatalogoProjection> catalogo();

    @Query(value = """
            SELECT
            idMedicamento,
            codigoMedicamento,
            nombreMedicamento,
            descripcion
            FROM MedicamentoCatalogo
            """, nativeQuery = true)
    List<MedicamentoCatalogoListarProjection> listar();

    @Query(value = """
            SELECT
            idMedicamento,
            codigoMedicamento,
            nombreMedicamento,
            descripcion,
            concentracion,
            presentacion
            FROM MedicamentoCatalogo
            WHERE idMedicamento = :idMedicamento
            """, nativeQuery = true)
    MedicamentoCatalogoDetalleProjection detalle(@Param("idMedicamento") Integer idMedicamento);
}
