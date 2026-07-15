package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracionSistemaRepository extends JpaRepository<ConfiguracionSistema, Integer> {
    Optional<ConfiguracionSistema> findByIdAsociado(Integer idAsociado);
}
