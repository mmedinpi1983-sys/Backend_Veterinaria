package com.utp.sistemaclinicaveterinaria.modulos.EstadoProgramacion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EstadoProgramacion")
@Getter
@Setter
@NoArgsConstructor
public class EstadoProgramacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstadoProgramacion", nullable = false)
    private Integer idEstadoProgramacion;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}