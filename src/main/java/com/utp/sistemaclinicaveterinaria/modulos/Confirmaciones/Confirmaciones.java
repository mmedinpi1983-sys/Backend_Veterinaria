package com.utp.sistemaclinicaveterinaria.modulos.Confirmaciones;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Confirmaciones")
@Getter
@Setter
@NoArgsConstructor
public class Confirmaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConfirmacion", nullable = false)
    private Integer idConfirmacion;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
}
