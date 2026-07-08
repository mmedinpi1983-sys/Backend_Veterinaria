package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DiagnosticoCatalogo")
@Getter
@Setter
@NoArgsConstructor
public class DiagnosticoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiagnosticoCatalogo", nullable = false)
    private Integer idDiagnosticoCatalogo;

    @Column(name = "codigoDiagnostico", nullable = false, length = 500)
    private String codigoDiagnostico;

    @Column(name = "nombreDiagnostico", nullable = false, length = 500)
    private String nombreDiagnostico;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
}
