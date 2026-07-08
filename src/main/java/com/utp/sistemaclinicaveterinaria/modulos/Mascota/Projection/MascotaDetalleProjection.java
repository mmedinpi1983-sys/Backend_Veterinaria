package com.utp.sistemaclinicaveterinaria.modulos.Mascota.Projection;

import java.time.LocalDateTime;
import java.time.LocalDate;

public interface MascotaDetalleProjection {
    Integer getIdMascota();
    String getNombre();
    Integer getIdEspecie();
    Integer getIdRaza();
    LocalDate getFechaNacimiento();
    String getSexo();
    String getTamanio();
    String getNotas();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
