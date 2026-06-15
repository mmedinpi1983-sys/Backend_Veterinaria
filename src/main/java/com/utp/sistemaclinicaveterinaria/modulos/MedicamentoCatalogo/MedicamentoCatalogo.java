package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MedicamentoCatalogo")
@Getter
@Setter
@NoArgsConstructor
public class MedicamentoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedicamento", nullable = false)
    private Integer idMedicamento;

    @Column(name = "codigoMedicamento", nullable = false, length = 500)
    private String codigoMedicamento;

    @Column(name = "nombreMedicamento", nullable = false, length = 500)
    private String nombreMedicamento;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "concentracion", nullable = false, length = 500)
    private String concentracion;

    @Column(name = "presentacion", nullable = false, length = 500)
    private String presentacion;

}