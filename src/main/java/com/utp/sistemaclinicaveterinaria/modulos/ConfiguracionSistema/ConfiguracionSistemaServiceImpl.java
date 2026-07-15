package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema.ConfiguracionSistemaDTO.ConfiguracionSistemaUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class ConfiguracionSistemaServiceImpl implements ConfiguracionSistemaService {
    private final ConfiguracionSistemaRepository r;
    private final ConfiguracionSistemaMapper m;

    public ConfiguracionSistemaServiceImpl(ConfiguracionSistemaRepository r, ConfiguracionSistemaMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public ConfiguracionSistemaResponse obtenerMia() {
        return r.findByIdAsociado(UsuarioActual.getAsociadoId()).map(m::toResponse).orElse(null);
    }

    @Override
    public void crear(ConfiguracionSistemaCreateRequest c) {
        ConfiguracionSistema entity = m.toEntity(c);
        entity.setIdAsociado(UsuarioActual.getAsociadoId());
        entity.setFechaCreacion(LocalDateTime.now());
        r.save(entity);
    }

    @Override
    public void actualizar(Integer id, ConfiguracionSistemaUpdateRequest u) {
        ConfiguracionSistema entity = r.getReferenceById(id);
        m.updateEntity(entity, u);
        entity.setFechaModificacion(LocalDateTime.now());
        r.save(entity);
    }
}
