package com.utp.sistemaclinicaveterinaria.modulos.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Integer> {
    List<Presentacion> findByFechaEliminacionIsNull();
    Optional<Presentacion> findByIdPresentacionAndFechaEliminacionIsNull(Integer idPresentacion);
}
