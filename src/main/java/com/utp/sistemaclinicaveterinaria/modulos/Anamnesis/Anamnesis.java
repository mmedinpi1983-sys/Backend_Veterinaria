package com.utp.sistemaclinicaveterinaria.modulos.Anamnesis;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Anamnesis")
@Getter
@Setter
@NoArgsConstructor
public class Anamnesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnamnesis", nullable = false)
    private Integer idAnamnesis;

    @Column(name = "id_Consulta", nullable = false)
    private Integer idConsulta;

    @Column(name = "antecedentes", length = 500)
    private String antecedentes;

    @Column(name = "alergias", nullable = false)
    private Integer alergias;

    @Column(name = "cirugiasAnteriores", nullable = false)
    private Integer cirugiasAnteriores;

    @Column(name = "medicamentosActuales", length = 500)
    private String medicamentosActuales;

    @Column(name = "alimentacion", length = 500)
    private String alimentacion;

    @Column(name = "comportamiento", length = 500)
    private String comportamiento;

    @Column(name = "inicioSintomas", length = 500)
    private String inicioSintomas;

    @Column(name = "evolucionSintomas", length = 500)
    private String evolucionSintomas;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "detalleAlergias", length = 500)
    private String detalleAlergias;

    @Column(name = "detalleCirugias", length = 500)
    private String detalleCirugias;

    @Column(name = "historialVacunacion", length = 500)
    private String historialVacunacion;

    @Column(name = "estiloVida", length = 500)
    private String estiloVida;

    @Column(name = "historialReproductivo", nullable = false)
    private Integer historialReproductivo;

    @Column(name = "reproduccionDetalle", length = 500)
    private String reproduccionDetalle;

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