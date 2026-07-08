package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle.Projection;

import java.time.LocalDateTime;

public interface RecetaDetalleDetalleProjection {
    Integer getIdRecetaDetalle();
    Integer getIdReceta();
    Integer getIdMedicamento();
    String getDosis();
    String getFrecuencia();
    String getDuracion();
    Integer getViaAdministracion();
    String getIndicacionesEspecificas();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
