package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
    List<Dueno> findByFechaEliminacionIsNull();
    Optional<Dueno> findByIdDuenoAndFechaEliminacionIsNull(Integer idDueno);
    // Para validar que el DNI no se repita entre duenos activos
    boolean existsByNroDocumentoAndFechaEliminacionIsNull(String nroDocumento);
    boolean existsByNroDocumentoAndIdDuenoNotAndFechaEliminacionIsNull(String nroDocumento, Integer idDueno);
}
