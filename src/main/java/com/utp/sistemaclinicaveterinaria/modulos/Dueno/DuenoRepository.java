package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
    List<Dueno> findByFechaEliminacionIsNull();
    Optional<Dueno> findByIdDuenoAndFechaEliminacionIsNull(Integer idDueno);
}
