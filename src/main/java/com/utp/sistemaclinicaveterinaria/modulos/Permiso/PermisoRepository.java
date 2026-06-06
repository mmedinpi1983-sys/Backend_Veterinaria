package com.utp.sistemaclinicaveterinaria.modulos.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByFechaEliminacionIsNull();
    Optional<Permiso> findByIdPermisoAndFechaEliminacionIsNull(Integer idPermiso);
}
