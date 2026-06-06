package com.utp.sistemaclinicaveterinaria.modulos.Atencion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Atencion")
@Getter
@Setter
@NoArgsConstructor
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAtencion", nullable = false)
    private Integer idAtencion;

    @Column(name = "id_CitaProgramada")
    private Integer idCitaProgramada;

    @Column(name = "id_Triaje")
    private Integer idTriaje;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "fechaAtencion", nullable = false)
    private LocalDate fechaAtencion;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    @Column(name = "id_EstadoSalida", nullable = false)
    private Integer idEstadoSalida;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_EstadoAtencion", nullable = false)
    private Integer idEstadoAtencion;

    @Column(name = "id_Mascota", nullable = false)
    private Integer idMascota;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}