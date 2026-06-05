package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface EspecieRazaRepository extends JpaRepository<EspecieRaza, Integer> {
    List<EspecieRaza> findByFechaEliminacionIsNull();
    Optional<EspecieRaza> findByIdEspecieRazaAndFechaEliminacionIsNull(Integer idEspecieRaza);
}
