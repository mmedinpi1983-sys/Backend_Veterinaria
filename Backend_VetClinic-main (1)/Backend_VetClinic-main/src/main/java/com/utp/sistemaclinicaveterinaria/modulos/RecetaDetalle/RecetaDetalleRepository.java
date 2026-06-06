package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Integer> {
    List<RecetaDetalle> findByFechaEliminacionIsNull();
    Optional<RecetaDetalle> findByIdRecetaDetalleAndFechaEliminacionIsNull(Integer idRecetaDetalle);
    List<RecetaDetalle> findByIdRecetaAndFechaEliminacionIsNull(Integer idReceta);
}
