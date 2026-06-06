package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AtencionEsteticaRepository extends JpaRepository<AtencionEstetica, Integer> {
    List<AtencionEstetica> findByFechaEliminacionIsNull();
    Optional<AtencionEstetica> findByIdEsteticaAndFechaEliminacionIsNull(Integer idEstetica);
}
