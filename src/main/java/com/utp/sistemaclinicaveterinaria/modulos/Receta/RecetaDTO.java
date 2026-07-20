package com.utp.sistemaclinicaveterinaria.modulos.Receta;
import java.time.*;

public interface RecetaDTO {
    record RecetaCatalogResponse(Integer idReceta, LocalDateTime fechaReceta) {}
    record RecetaListResponse(Integer idReceta, LocalDateTime fechaReceta, LocalDateTime fechaCreacion) {}
    record RecetaDetailResponse(
            Integer idReceta, Integer idConsulta, LocalDateTime fechaReceta, Integer idEmpleadoAsociado,
            String usuarioCreador, LocalDateTime fechaCreacion,
            String usuarioModificador, LocalDateTime fechaModificacion,
            String usuarioEliminador, LocalDateTime fechaEliminacion) {}
    record RecetaCreateRequest(Integer idConsulta, LocalDateTime fechaReceta, Integer idEmpleadoAsociado) {}
    record RecetaCreateResponse(Integer idReceta) {}
    record RecetaUpdateRequest(Integer idConsulta, LocalDateTime fechaReceta, Integer idEmpleadoAsociado) {}
    record RecetaDeleteRequest(Integer idReceta) {}
}
