package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import java.time.*;
import java.util.List;
import jakarta.validation.constraints.*;
public interface MascotaDTO {

    record MascotaCatalogResponse(
            Integer idMascota,
            String nombre,
            String sexo,
            String tamanio) {
    }

    record MascotaListResponse(
            Integer idMascota,
            String nombre,
            String sexo,
            String tamanio,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record MascotaDetailResponse(
            Integer idMascota,
            String nombre,
            Integer idEspecie,
            Integer idRaza,
            LocalDate fechaNacimiento,
            String sexo,
            String tamanio,
            String notas,
            Boolean estado,
            String empleadoCreador,
            LocalDateTime fechaCreacion,
            String empleadoModificador,
            LocalDateTime fechaModificacion,
            String empleadoEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record MascotaCreateRequest(
            @NotBlank(message = "El nombre es obligatorio")
            @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
            String nombre,
            @NotNull(message = "La especie es obligatoria")
            Integer idEspecie,
            @NotNull(message = "La raza es obligatoria")
            Integer idRaza,
            @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
            LocalDate fechaNacimiento,
            @Pattern(regexp = "^(Macho|Hembra)$", message = "El sexo debe ser Macho o Hembra")
            String sexo,
            @Pattern(regexp = "^(Pequeño|Mediano|Grande)$", message = "El tamaño debe ser Pequeño, Mediano o Grande")
            String tamanio,
            String notas) {
    }

    record MascotaUpdateRequest(
            String nombre,
            Integer idEspecie,
            Integer idRaza,
            LocalDate fechaNacimiento,
            String sexo,
            String tamanio,
            String notas,
            Boolean estado) {
    }

    record MascotaFilterRequest(
            String q,
            Boolean estado) {
    }

    record MascotaDeleteRequest(
            Integer idMascota) {
    }

    record MascotaCreateResponse(
            Integer idMascota) {
    }

    record SearchItem(
            Integer idMascota,
            String nombre,
            String especie,
            String raza,
            Integer idDueno,
            String nombreDueno,
            String tamanio,
            String sexo) {
    }

    record ListSearchResponse(List<SearchItem> items) {}
}
