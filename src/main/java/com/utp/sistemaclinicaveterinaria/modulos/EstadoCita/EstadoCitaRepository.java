package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Integer> {
}
