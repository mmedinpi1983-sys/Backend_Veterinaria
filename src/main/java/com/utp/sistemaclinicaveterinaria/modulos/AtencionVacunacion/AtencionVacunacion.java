package com.utp.sistemaclinicaveterinaria.modulos.AtencionVacunacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AtencionVacunacion")
@Getter
@Setter
@NoArgsConstructor
public class AtencionVacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVacunacion", nullable = false)
    private Integer idVacunacion;

    @Column(name = "id_Atencion")
    private Integer idAtencion;

    @Column(name = "id_Vacuna", nullable = false)
    private Integer idVacuna;

    @Column(name = "dosis", nullable = false)
    private Integer dosis;

    @Column(name = "fechaAplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "fechaRefuerzo")
    private LocalDate fechaRefuerzo;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_Consulta")
    private Integer idConsulta;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}