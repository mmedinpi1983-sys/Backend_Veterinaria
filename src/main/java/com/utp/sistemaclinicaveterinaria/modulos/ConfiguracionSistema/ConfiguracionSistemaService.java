package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaUpdateRequest;

public interface ConfiguracionSistemaService {
    ConfiguracionSistemaResponse obtenerMia();
    void crear(ConfiguracionSistemaCreateRequest c);
    void actualizar(Integer id, ConfiguracionSistemaUpdateRequest u);
}
