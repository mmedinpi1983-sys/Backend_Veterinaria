package com.utp.sistemaclinicaveterinaria.modulos.Asociado.Projection;

import java.time.LocalDateTime;

public interface AsociadoDetalleProjection {
    Integer getIdAsociado();
    String getNombre();
    String getRuc();
    String getNombreDueno();
    String getApellidoDueno();
    Integer getIdNivelSuscripcion();
    Boolean getActivo();
    String getCorreoElectronico();
    String getNroTelefono();
    String getDireccion();
    String getDiasAtencion();
    String getSuperAdminCreador();
    LocalDateTime getFechaCreacion();
    String getSuperAdminModificador();
    LocalDateTime getFechaModificacion();
    String getSuperAdminEliminador();
    LocalDateTime getFechaEliminacion();
}
