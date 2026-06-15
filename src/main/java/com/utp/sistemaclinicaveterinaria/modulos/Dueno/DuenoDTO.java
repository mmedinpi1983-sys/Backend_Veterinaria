package com.utp.sistemaclinicaveterinaria.modulos.Dueno;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.*;
public interface DuenoDTO {
    record Request(
                 Integer idDocumentoIdentidad,
                 Integer idAsociado,
                 @NotBlank(message = "El nombre es obligatorio")
                 @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
                 @Pattern(regexp = "^\\p{L}+([ '.-]\\p{L}+)*$", message = "El nombre solo permite letras y espacios")
                 String nombre,
                 @NotBlank(message = "El apellido paterno es obligatorio")
                 @Size(max = 100, message = "El apellido no puede superar 100 caracteres")
                 @Pattern(regexp = "^\\p{L}+([ '.-]\\p{L}+)*$", message = "El apellido solo permite letras y espacios")
                 String apellidoPaterno,
                 @NotBlank(message = "El apellido materno es obligatorio")
                 @Size(max = 100, message = "El apellido no puede superar 100 caracteres")
                 @Pattern(regexp = "^\\p{L}+([ '.-]\\p{L}+)*$", message = "El apellido solo permite letras y espacios")
                 String apellidoMaterno,
                 @NotBlank(message = "El DNI es obligatorio")
                 @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos")
                 String nroDocumento,
                 @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener 9 dígitos")
                 String nroTelefono,
                 @Email(message = "El correo no tiene un formato válido")
                 @Size(max = 100, message = "El correo no puede superar 100 caracteres")
                 String correoElectronico,
                 Boolean estado
    ) {}
    record Response(
                 Integer idDueno,
                 Integer idDocumentoIdentidad,
                 Integer idAsociado,
                 String nombre,
                 String apellidoPaterno,
                 String apellidoMaterno,
                 String nroDocumento,
                 String nroTelefono,
                 String correoElectronico,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Boolean estado,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idDueno,
                 String nombre,
                 String apellidoPaterno,
                 String apellidoMaterno,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
