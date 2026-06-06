package com.utp.sistemaclinicaveterinaria.modulos.RolesClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface RolesClinicaRepository extends JpaRepository<RolesClinica, Integer> {
    List<RolesClinica> findByFechaEliminacionIsNull();
    Optional<RolesClinica> findByIdRolesAndFechaEliminacionIsNull(Integer idRoles);
}
