package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {
    List<RolPermiso> findByFechaEliminacionIsNull();
    Optional<RolPermiso> findByIdRolPermisoAndFechaEliminacionIsNull(Integer idRolPermiso);
}
