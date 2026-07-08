package com.utp.sistemaclinicaveterinaria.modulos.ClaseMovimiento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ClaseMovimiento")
@Getter
@Setter
@NoArgsConstructor
public class ClaseMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClaseMovimiento", nullable = false)
    private Integer idClaseMovimiento;

    @Column(name = "descripcion", length = 500)
    private String descripcion;
}
