package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MetodoIngreso")
@Getter
@Setter
@NoArgsConstructor
public class MetodoIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMetodoIngreso", nullable = false)
    private Integer idMetodoIngreso;

    @Column(name = "nombre", length = 500)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "id_Asociado")
    private Integer idAsociado;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}