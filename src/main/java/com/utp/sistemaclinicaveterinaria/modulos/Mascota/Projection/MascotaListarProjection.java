package com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection;

import java.time.LocalDateTime;

public interface MascotaListarProjection {
    Integer getIdMascota();
    String getNombre();
    String getSexo();
    String getTamanio();
    Boolean getEstado();
    LocalDateTime getFechaCreacion();
}
