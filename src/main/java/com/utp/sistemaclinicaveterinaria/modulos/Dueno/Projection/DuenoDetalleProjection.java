package com.utp.sistemaclinicaveterinaria.modulos.Dueno.Projection;

import java.time.LocalDateTime;

public interface DuenoDetalleProjection {
    Integer getIdDueno();
    Integer getIdDocumentoIdentidad();
    String getNombre();
    String getApellidoPaterno();
    String getApellidoMaterno();
    String getNroDocumento();
    String getNroTelefono();
    String getCorreoElectronico();
    Boolean getEstado();
    String getEmpleadoCreador();
    LocalDateTime getFechaCreacion();
    String getEmpleadoModificador();
    LocalDateTime getFechaModificacion();
    String getEmpleadoEliminador();
    LocalDateTime getFechaEliminacion();
}
