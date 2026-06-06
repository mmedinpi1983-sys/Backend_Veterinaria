package com.utp.sistemaclinicaveterinaria.modulos.AtencionEstetica;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AtencionEstetica")
@Getter
@Setter
@NoArgsConstructor
public class AtencionEstetica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstetica", nullable = false)
    private Integer idEstetica;

    @Column(name = "id_Atencion", nullable = false)
    private Integer idAtencion;

    @Column(name = "id_Servicio", nullable = false)
    private Integer idServicio;

    @Column(name = "detalleServicio", nullable = false, length = 500)
    private String detalleServicio;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

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