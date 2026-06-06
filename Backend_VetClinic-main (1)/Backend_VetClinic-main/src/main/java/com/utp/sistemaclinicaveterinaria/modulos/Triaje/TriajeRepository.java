package com.utp.sistemaclinicaveterinaria.modulos.Triaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface TriajeRepository extends JpaRepository<Triaje, Integer> {
    List<Triaje> findByFechaEliminacionIsNull();
    Optional<Triaje> findByIdTriajeAndFechaEliminacionIsNull(Integer idTriaje);
    Optional<Triaje> findByIdCitaProgramadaAndFechaEliminacionIsNull(Integer idCitaProgramada);
}
