package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface MedicamentoCatalogoRepository extends JpaRepository<MedicamentoCatalogo, Integer> {
}
