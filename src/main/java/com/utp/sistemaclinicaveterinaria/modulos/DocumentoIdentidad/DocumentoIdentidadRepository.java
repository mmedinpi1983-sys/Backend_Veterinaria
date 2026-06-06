package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DocumentoIdentidadRepository extends JpaRepository<DocumentoIdentidad, Integer> {
}
