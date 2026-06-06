package com.utp.sistemaclinicaveterinaria.modulos.AtencionConsulta;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AtencionConsulta")
@Getter
@Setter
@NoArgsConstructor
public class AtencionConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsulta", nullable = false)
    private Integer idConsulta;

    @Column(name = "id_Atencion", nullable = false)
    private Integer idAtencion;

    @Column(name = "evaluacionClinica", nullable = false, length = 500)
    private String evaluacionClinica;

    @Column(name = "tratamiento", nullable = false, length = 500)
    private String tratamiento;

    @Column(name = "indicaciones", nullable = false, length = 500)
    private String indicaciones;

    @Column(name = "observaciones", nullable = false, length = 500)
    private String observaciones;

    @Column(name = "requiereControl", nullable = false)
    private Boolean requiereControl;

    @Column(name = "fechaProximoControl")
    private LocalDate fechaProximoControl;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "motivoConsulta", nullable = false, length = 500)
    private String motivoConsulta;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}