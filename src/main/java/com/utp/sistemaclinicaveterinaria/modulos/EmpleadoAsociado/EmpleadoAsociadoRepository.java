package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface EmpleadoAsociadoRepository extends JpaRepository<EmpleadoAsociado, Integer> {
    List<EmpleadoAsociado> findByFechaEliminacionIsNull();
    Optional<EmpleadoAsociado> findByIdEmpleadoAsociadoAndFechaEliminacionIsNull(Integer idEmpleadoAsociado);
}
