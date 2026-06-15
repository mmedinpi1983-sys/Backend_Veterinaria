package com.utp.sistemaclinicaveterinaria.modulos.Programacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface ProgramacionRepository extends JpaRepository<Programacion, Integer> {
    List<Programacion> findByFechaEliminacionIsNull();
    Optional<Programacion> findByIdProgramacionAndFechaEliminacionIsNull(Integer idProgramacion);
}
