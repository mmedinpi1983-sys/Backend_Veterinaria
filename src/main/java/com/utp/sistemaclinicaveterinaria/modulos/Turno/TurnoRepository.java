package com.utp.sistemaclinicaveterinaria.modulos.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    List<Turno> findByFechaEliminacionIsNull();
    Optional<Turno> findByIdTurnoAndFechaEliminacionIsNull(Integer idTurno);
}
