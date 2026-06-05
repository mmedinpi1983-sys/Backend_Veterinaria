package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface DuenoMascotaRepository extends JpaRepository<DuenoMascota, Integer> {
    List<DuenoMascota> findByFechaEliminacionIsNull();
    Optional<DuenoMascota> findByIdDuenoMascotaAndFechaEliminacionIsNull(Integer idDuenoMascota);
}
