package com.utp.sistemaclinicaveterinaria.modulos.Asociado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AsociadoRepository extends JpaRepository<Asociado, Integer> {
    List<Asociado> findByFechaEliminacionIsNull();
    Optional<Asociado> findByIdAsociadoAndFechaEliminacionIsNull(Integer idAsociado);
}
