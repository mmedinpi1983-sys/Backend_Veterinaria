package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis.Projection;

import java.time.LocalDateTime;

public interface AnamnesisDetalleProjection {
    Integer getIdAnamnesis();
    Integer getIdConsulta();
    String getAntecedentes();
    Integer getAlergias();
    Integer getCirugiasAnteriores();
    String getMedicamentosActuales();
    String getAlimentacion();
    String getComportamiento();
    String getInicioSintomas();
    String getEvolucionSintomas();
    String getObservaciones();
    String getDetalleAlergias();
    String getDetalleCirugias();
    String getHistorialVacunacion();
    String getEstiloVida();
    Integer getHistorialReproductivo();
    String getReproduccionDetalle();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
