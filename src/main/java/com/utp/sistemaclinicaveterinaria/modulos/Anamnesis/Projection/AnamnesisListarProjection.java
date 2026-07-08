package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection;

import java.time.LocalDateTime;

public interface AnamnesisListarProjection {
    Integer getIdAnamnesis();
    Integer getIdConsulta();
    String getAntecedentes();
    String getMedicamentosActuales();
    String getAlimentacion();
    LocalDateTime getFechaCreacion();
}
