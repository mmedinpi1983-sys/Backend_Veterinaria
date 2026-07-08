package com.utp.sistemaclinicaveterinaria.modulos.ConsultaDiagnostico;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Consulta_Diagnostico")
@Getter
@Setter
@NoArgsConstructor
public class ConsultaDiagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiagnostico", nullable = false)
    private Integer idDiagnostico;

    @Column(name = "id_Consulta", nullable = false)
    private Integer idConsulta;

    @Column(name = "id_DiagnosticoCatalogo", nullable = false)
    private Integer idDiagnosticoCatalogo;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;
}
