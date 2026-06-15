package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CitaProgramada")
@Getter
@Setter
@NoArgsConstructor
public class CitaProgramada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCitaProgramada", nullable = false)
    private Integer idCitaProgramada;

    @Column(name = "id_Dueno", nullable = false)
    private Integer idDueno;

    @Column(name = "id_Programacion", nullable = false)
    private Integer idProgramacion;

    @Column(name = "id_Mascota", nullable = false)
    private Integer idMascota;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "id_EstadoCita", nullable = false)
    private Integer idEstadoCita;

    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "id_Asociado")
    private Integer idAsociado;

    @Column(name = "id_Categoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "id_Servicio", nullable = false)
    private Integer idServicio;

    @Column(name = "motivoReprogramacion", length = 500)
    private String motivoReprogramacion;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}