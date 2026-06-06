package com.utp.sistemaclinicaveterinaria.modulos.RecetaDetalle;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RecetaDetalle")
@Getter
@Setter
@NoArgsConstructor
public class RecetaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecetaDetalle", nullable = false)
    private Integer idRecetaDetalle;

    @Column(name = "id_Receta", nullable = false)
    private Integer idReceta;

    @Column(name = "id_Medicamento", nullable = false)
    private Integer idMedicamento;

    @Column(name = "dosis", nullable = false, length = 500)
    private String dosis;

    @Column(name = "frecuencia", nullable = false, length = 500)
    private String frecuencia;

    @Column(name = "duracion", nullable = false, length = 500)
    private String duracion;

    @Column(name = "viaAdministracion", nullable = false)
    private Integer viaAdministracion;

    @Column(name = "indicacionesEspecificas", length = 500)
    private String indicacionesEspecificas;

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