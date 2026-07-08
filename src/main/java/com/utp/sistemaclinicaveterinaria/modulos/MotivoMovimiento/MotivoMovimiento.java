package com.utp.sistemaclinicaveterinaria.modulos.MotivoMovimiento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MotivoMovimiento")
@Getter
@Setter
@NoArgsConstructor
public class MotivoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMotivoMovimiento", nullable = false)
    private Integer idMotivoMovimiento;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
}
