package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection;

import java.time.LocalDateTime;

public interface DuenoMascotaDetalleProjection {
    Integer getIdDuenoMascota();
    Integer getIdDueno();
    Integer getIdMascota();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
