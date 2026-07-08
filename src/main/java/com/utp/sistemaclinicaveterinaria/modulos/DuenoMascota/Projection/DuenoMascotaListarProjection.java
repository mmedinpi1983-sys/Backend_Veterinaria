package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota.Projection;

import java.time.LocalDateTime;

public interface DuenoMascotaListarProjection {
    Integer getIdDuenoMascota();
    Integer getIdDueno();
    Integer getIdMascota();
    LocalDateTime getFechaCreacion();
}
