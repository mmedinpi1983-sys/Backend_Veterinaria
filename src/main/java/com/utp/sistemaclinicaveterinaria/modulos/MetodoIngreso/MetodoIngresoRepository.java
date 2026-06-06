package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface MetodoIngresoRepository extends JpaRepository<MetodoIngreso, Integer> {
}
