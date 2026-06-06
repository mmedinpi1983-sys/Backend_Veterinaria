package com.utp.sistemaclinicaveterinaria.modulos.EmpleadoAsociado;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EmpleadoAsociado")
@Getter
@Setter
@NoArgsConstructor
public class EmpleadoAsociado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleadoAsociado", nullable = false)
    private Integer idEmpleadoAsociado;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "correo", nullable = false, length = 255)
    private String correo;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "nombreEmpleado", nullable = false, length = 255)
    private String nombreEmpleado;

    @Column(name = "apellidoPaterno", nullable = false, length = 255)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 255)
    private String apellidoMaterno;

    @Column(name = "nroDocumento", nullable = false, length = 255)
    private String nroDocumento;

    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDateTime fechaNacimiento;

    @Column(name = "id_DocumentoIdentidad", nullable = false)
    private Integer idDocumentoIdentidad;

    @Column(name = "id_RolesClinica", nullable = false)
    private Integer idRolesClinica;

    @Column(name = "correoElectronico", length = 255)
    private String correoElectronico;

    @Column(name = "nroTelefono", length = 20)
    private String nroTelefono;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_EmpleadoCreador")
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}