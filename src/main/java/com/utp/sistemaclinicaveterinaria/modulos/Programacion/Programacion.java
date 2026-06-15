package com.utp.sistemaclinicaveterinaria.modulos.Programacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Programacion")
@Getter
@Setter
@NoArgsConstructor
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProgramacion", nullable = false)
    private Integer idProgramacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "id_Turno", nullable = false)
    private Integer idTurno;

    @Column(name = "id_EmpleadoRegistrador", nullable = false)
    private Integer idEmpleadoRegistrador;

    @Column(name = "id_EstadoProgramacion", nullable = false)
    private Integer idEstadoProgramacion;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "id_Categoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "id_Servicio", nullable = false)
    private Integer idServicio;

    @Column(name = "ambiente", length = 255)
    private String ambiente;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}