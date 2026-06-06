package com.utp.sistemaclinicaveterinaria.modulos.Triaje;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Triaje")
@Getter
@Setter
@NoArgsConstructor
public class Triaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTriaje", nullable = false)
    private Integer idTriaje;

    @Column(name = "id_CitaProgramada")
    private Integer idCitaProgramada;

    @Column(name = "codigoTemporal", length = 500)
    private String codigoTemporal;

    @Column(name = "id_Mascota")
    private Integer idMascota;

    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "id_MetodoIngreso")
    private Integer idMetodoIngreso;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;

}