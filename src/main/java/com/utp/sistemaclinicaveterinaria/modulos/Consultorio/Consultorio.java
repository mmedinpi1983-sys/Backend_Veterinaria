package com.utp.sistemaclinicaveterinaria.modulos.Consultorio;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Consultorio")
@Getter
@Setter
@NoArgsConstructor
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsultorio", nullable = false)
    private Integer idConsultorio;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "piso", length = 50)
    private String piso;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

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
