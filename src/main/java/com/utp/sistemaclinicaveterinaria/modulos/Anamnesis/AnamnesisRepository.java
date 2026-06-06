package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Integer> {
    List<Anamnesis> findByFechaEliminacionIsNull();
    Optional<Anamnesis> findByIdAnamnesisAndFechaEliminacionIsNull(Integer idAnamnesis);
    Optional<Anamnesis> findByIdConsultaAndFechaEliminacionIsNull(Integer idConsulta);
}
