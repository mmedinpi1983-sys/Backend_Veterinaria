package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;
import java.time.*;
public interface DuenoMascotaDTO {

    record DuenoMascotaCatalogResponse(
            Integer idDuenoMascota,
            Integer idDueno,
            Integer idMascota) {
    }

    record DuenoMascotaListResponse(
            Integer idDuenoMascota,
            Integer idDueno,
            Integer idMascota,
            LocalDateTime fechaCreacion) {
    }

    record DuenoMascotaDetailResponse(
            Integer idDuenoMascota,
            Integer idDueno,
            Integer idMascota,
            String empleadoCreador,
            LocalDateTime fechaCreacion,
            String empleadoModificador,
            LocalDateTime fechaModificacion,
            String empleadoEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record DuenoMascotaCreateRequest(
            Integer idDueno,
            Integer idMascota) {
    }

    record DuenoMascotaUpdateRequest(
            Integer idDueno,
            Integer idMascota) {
    }

    record DuenoMascotaDeleteRequest(
            Integer idDuenoMascota) {
    }
}
