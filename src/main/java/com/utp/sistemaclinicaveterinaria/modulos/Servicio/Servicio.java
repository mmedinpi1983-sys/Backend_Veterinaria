package com.utp.sistemaclinicaveterinaria.modulos.Servicio;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Servicio")
@Getter
@Setter
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idServicio", nullable = false)
    private Integer idServicio;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "id_Categoria")
    private Integer idCategoria;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "requiereTriaje", nullable = false)
    private Boolean requiereTriaje;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "duracionEstimada")
    private Integer duracionEstimada;

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