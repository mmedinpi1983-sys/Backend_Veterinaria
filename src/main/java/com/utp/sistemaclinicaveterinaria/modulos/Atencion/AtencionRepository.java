package com.utp.sistemaclinicaveterinaria.modulos.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Integer> {
    List<Atencion> findByFechaEliminacionIsNull();
    Optional<Atencion> findByIdAtencionAndFechaEliminacionIsNull(Integer idAtencion);
    Optional<Atencion> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);

    @Query(value = """
        SELECT
            a.idAtencion,
            CONVERT(VARCHAR, a.fechaAtencion, 23) AS fechaAtencion,
            ISNULL(ac.evaluacionClinica, '') AS evaluacionClinica,
            ISNULL(ac.tratamiento, '') AS tratamiento,
            ISNULL(CONVERT(VARCHAR, ac.fechaProximoControl, 23), '') AS fechaProximoControl,
            ISNULL(ac.motivoConsulta, '') AS motivoConsulta
        FROM Atencion a
        LEFT JOIN AtencionConsulta ac ON a.idAtencion = ac.id_Atencion AND ac.fechaEliminacion IS NULL
        WHERE a.id_Mascota = :idMascota AND a.fechaEliminacion IS NULL
        ORDER BY a.fechaAtencion DESC
        """, nativeQuery = true)
    List<HistorialView> findHistorialByIdMascota(@Param("idMascota") Integer idMascota);
}
