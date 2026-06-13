package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface EstadoProgramacionRepository extends JpaRepository<EstadoProgramacion, Integer> {
}
