package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

// Repositorio de mascotas - incluye query nativa para búsqueda enriquecida con datos del dueño
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    // Obtiene todas las mascotas no eliminadas
    List<Mascota> findByFechaEliminacionIsNull();

    // Busca una mascota activa por su ID
    Optional<Mascota> findByIdMascotaAndFechaEliminacionIsNull(Integer idMascota);

    // Búsqueda en tiempo real: une Mascota con Dueño, Especie y Raza
    // COALESCE prioriza el texto libre (especieTexto/razaTexto) sobre los IDs de catálogo
    // ISNULL protege la concatenación del nombre del dueño contra valores nulos
    // Filtra por nombre de mascota, nombre o apellido del dueño
    @Query(value = """
        SELECT
            m.idMascota,
            m.nombre,
            er_esp.nombre AS especie,
            er_raza.nombre AS raza,
            dm.id_Dueno AS idDueno,
            ISNULL(d.nombre,'') + ' ' + ISNULL(d.apellidoPaterno,'') + ' ' + ISNULL(d.apellidoMaterno,'') AS nombreDueno,
            m.tamanio,
            m.sexo
        FROM Mascota m
        LEFT JOIN EspecieRaza er_esp ON m.id_Especie = er_esp.idEspecieRaza
        LEFT JOIN EspecieRaza er_raza ON m.id_Raza = er_raza.idEspecieRaza
        LEFT JOIN Dueno_Mascota dm ON m.idMascota = dm.id_Mascota AND dm.fechaEliminacion IS NULL
        LEFT JOIN Dueno d ON dm.id_Dueno = d.idDueno AND d.fechaEliminacion IS NULL
        WHERE m.fechaEliminacion IS NULL
        AND (:q IS NULL OR m.nombre LIKE '%' + :q + '%' OR d.nombre LIKE '%' + :q + '%' OR d.apellidoPaterno LIKE '%' + :q + '%')
        ORDER BY m.nombre ASC
        """, nativeQuery = true)
    List<MascotaSearchView> buscarConDueno(@Param("q") String q);
}
