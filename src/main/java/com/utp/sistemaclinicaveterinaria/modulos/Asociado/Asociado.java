package com.utp.sistemaclinicaveterinaria.modulos.Asociado;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Asociado")
@Getter
@Setter
@NoArgsConstructor
public class Asociado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "ruc", nullable = false, length = 255)
    private String ruc;

    @Column(name = "nombreDueno", nullable = false, length = 255)
    private String nombreDueno;

    @Column(name = "apellidoDueno", nullable = false, length = 255)
    private String apellidoDueno;

    @Column(name = "id_NivelSuscripcion", nullable = false)
    private Integer idNivelSuscripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "correoElectronico", length = 255)
    private String correoElectronico;

    @Column(name = "nroTelefono", length = 20)
    private String nroTelefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "diasAtencion", length = 100)
    private String diasAtencion;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_SuperAdminCreador", nullable = false)
    private Integer idSuperAdminCreador;

    @Column(name = "id_SuperAdminModificador")
    private Integer idSuperAdminModificador;

    @Column(name = "id_SuperAdminEliminador")
    private Integer idSuperAdminEliminador;

}