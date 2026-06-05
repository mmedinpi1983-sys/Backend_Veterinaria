package com.utp.sistemaclinicaveterinaria.modulos.Dueno;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Dueno")
@Getter
@Setter
@NoArgsConstructor
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDueno", nullable = false)
    private Integer idDueno;

    @Column(name = "id_DocumentoIdentidad", nullable = false)
    private Integer idDocumentoIdentidad;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellidoPaterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(name = "nroDocumento", nullable = false, length = 20)
    private String nroDocumento;

    @Column(name = "nroTelefono", length = 20)
    private String nroTelefono;

    @Column(name = "correoElectronico", length = 100)
    private String correoElectronico;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}