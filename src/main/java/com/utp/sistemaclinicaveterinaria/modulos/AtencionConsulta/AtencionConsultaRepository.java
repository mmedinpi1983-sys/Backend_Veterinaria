package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AtencionConsultaRepository extends JpaRepository<AtencionConsulta, Integer> {
    List<AtencionConsulta> findByFechaEliminacionIsNull();
    Optional<AtencionConsulta> findByIdConsultaAndFechaEliminacionIsNull(Integer idConsulta);
    Optional<AtencionConsulta> findByIdAtencionAndFechaEliminacionIsNull(Integer idAtencion);
}
