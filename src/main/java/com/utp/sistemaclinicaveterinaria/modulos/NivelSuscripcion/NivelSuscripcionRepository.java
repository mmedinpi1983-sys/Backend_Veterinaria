package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface NivelSuscripcionRepository extends JpaRepository<NivelSuscripcion, Integer> {
    List<NivelSuscripcion> findByFechaEliminacionIsNull();
    Optional<NivelSuscripcion> findByIdNivelAndFechaEliminacionIsNull(Integer idNivel);
}
