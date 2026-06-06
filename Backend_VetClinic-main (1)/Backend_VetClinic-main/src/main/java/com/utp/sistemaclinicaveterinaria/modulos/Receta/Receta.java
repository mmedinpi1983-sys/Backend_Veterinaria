package com.utp.sistemaclinicaveterinaria.modulos.Receta;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Receta")
@Getter
@Setter
@NoArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReceta", nullable = false)
    private Integer idReceta;

    @Column(name = "id_Consulta", nullable = false)
    private Integer idConsulta;

    @Column(name = "fechaReceta", nullable = false)
    private LocalDateTime fechaReceta;

    @Column(name = "id_EmpleadoAsociado", nullable = false)
    private Integer idEmpleadoAsociado;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

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