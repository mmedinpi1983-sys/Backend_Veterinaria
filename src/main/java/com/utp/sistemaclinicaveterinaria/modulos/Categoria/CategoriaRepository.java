package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByFechaEliminacionIsNull();
    Optional<Categoria> findByIdCategoriaAndFechaEliminacionIsNull(Integer idCategoria);
}
