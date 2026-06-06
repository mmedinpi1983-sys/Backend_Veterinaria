package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface TriajeDetalleRepository extends JpaRepository<TriajeDetalle, Integer> {
    List<TriajeDetalle> findByFechaEliminacionIsNull();
    Optional<TriajeDetalle> findByIdTriajeDetalleAndFechaEliminacionIsNull(Integer idTriajeDetalle);
    Optional<TriajeDetalle> findByIdTriajeAndFechaEliminacionIsNull(Integer idTriaje);
}
