package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;
import java.time.*;

public interface RecetaDetalleDTO {
    record RecetaDetalleCatalogResponse(Integer idRecetaDetalle, String dosis, String frecuencia) {}
    record RecetaDetalleListResponse(Integer idRecetaDetalle, String dosis, String frecuencia, String duracion, LocalDateTime fechaCreacion) {}
    record RecetaDetalleDetailResponse(
            Integer idRecetaDetalle, Integer idReceta, Integer idMedicamento,
            String dosis, String frecuencia, String duracion,
            Integer viaAdministracion, String indicacionesEspecificas,
            String usuarioCreador, LocalDateTime fechaCreacion,
            String usuarioModificador, LocalDateTime fechaModificacion,
            String usuarioEliminador, LocalDateTime fechaEliminacion) {}
    record RecetaDetalleCreateRequest(Integer idReceta, Integer idMedicamento, String dosis, String frecuencia, String duracion, Integer viaAdministracion, String indicacionesEspecificas) {}
    record RecetaDetalleUpdateRequest(Integer idReceta, Integer idMedicamento, String dosis, String frecuencia, String duracion, Integer viaAdministracion, String indicacionesEspecificas) {}
    record RecetaDetalleDeleteRequest(Integer idRecetaDetalle) {}
}
