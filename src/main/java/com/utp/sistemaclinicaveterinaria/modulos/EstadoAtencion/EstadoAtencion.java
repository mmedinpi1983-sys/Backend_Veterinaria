package com.utp.sistemaclinicaveterinaria.modulos.EstadoAtencion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EstadoAtencion")
@Getter
@Setter
@NoArgsConstructor
public class EstadoAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstadoAtencion", nullable = false)
    private Integer idEstadoAtencion;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

}