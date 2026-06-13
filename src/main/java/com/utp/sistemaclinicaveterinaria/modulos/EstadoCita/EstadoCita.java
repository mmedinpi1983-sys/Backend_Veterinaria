package com.utp.sistemaclinicaveterinaria.modulos.EstadoCita;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EstadoCita")
@Getter
@Setter
@NoArgsConstructor
public class EstadoCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstadoCita", nullable = false)
    private Integer idEstadoCita;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

}