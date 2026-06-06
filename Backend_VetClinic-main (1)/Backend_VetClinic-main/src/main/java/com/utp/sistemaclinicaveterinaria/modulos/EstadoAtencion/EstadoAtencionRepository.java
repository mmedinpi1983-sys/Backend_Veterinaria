package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface EstadoAtencionRepository extends JpaRepository<EstadoAtencion, Integer> {
}
