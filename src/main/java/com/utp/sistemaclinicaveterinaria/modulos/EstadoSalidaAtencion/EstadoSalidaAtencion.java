package com.utp.sistemaclinicaveterinaria.modulos.EstadoSalidaAtencion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EstadoSalidaAtencion")
@Getter
@Setter
@NoArgsConstructor
public class EstadoSalidaAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstadoSalida", nullable = false)
    private Integer idEstadoSalida;

    @Column(name = "nombre", length = 500)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

}