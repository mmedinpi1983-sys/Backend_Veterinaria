package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface NivelPermisoRepository extends JpaRepository<NivelPermiso, Integer> {
    List<NivelPermiso> findByFechaEliminacionIsNull();
    Optional<NivelPermiso> findByIdNivelPermisoAndFechaEliminacionIsNull(Integer idNivelPermiso);
}
