package com.utp.sistemaclinicaveterinaria.modulos.TriajeDetalle;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TriajeDetalle")
@Getter
@Setter
@NoArgsConstructor
public class TriajeDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTriajeDetalle", nullable = false)
    private Integer idTriajeDetalle;

    @Column(name = "temperatura")
    private BigDecimal temperatura;

    @Column(name = "peso")
    private BigDecimal peso;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "alergias", length = 500)
    private String alergias;

    @Column(name = "id_Triaje")
    private Integer idTriaje;

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