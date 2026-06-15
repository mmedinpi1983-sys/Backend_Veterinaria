package com.utp.sistemaclinicaveterinaria.modulos.Mascota;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface MascotaDTO {
    record Request(
                 @NotBlank(message = "El nombre es obligatorio")
                 @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
                 String nombre,
                 @NotNull(message = "La especie es obligatoria")
                 Integer idEspecie,
                 @NotNull(message = "La raza es obligatoria")
                 Integer idRaza,
                 @NotNull(message = "El asociado es obligatorio")
                 Integer idAsociado,
                 @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
                 LocalDate fechaNacimiento,
                 @Pattern(regexp = "^(Macho|Hembra)$", message = "El sexo debe ser Macho o Hembra")
                 String sexo,
                 @Pattern(regexp = "^(Pequeño|Mediano|Grande)$", message = "El tamaño debe ser Pequeño, Mediano o Grande")
                 String tamanio,
                 String notas,
                 Boolean estado
    ) {}
    record Response(
                 Integer idMascota,
                 String nombre,
                 Integer idEspecie,
                 Integer idRaza,
                 Integer idAsociado,
                 LocalDate fechaNacimiento,
                 String sexo,
                 String tamanio,
                 String notas,
                 Boolean estado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idMascota,
                 String nombre,
                 String sexo,
                 String tamanio,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
    record SearchItem(
                 Integer idMascota,
                 String nombre,
                 String especie,
                 String raza,
                 Integer idDueno,
                 String nombreDueno,
                 String tamanio,
                 String sexo
    ) {}
}
