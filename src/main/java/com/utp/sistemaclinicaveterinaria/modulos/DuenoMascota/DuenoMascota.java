package com.utp.sistemaclinicaveterinaria.modulos.DuenoMascota;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Dueno_Mascota")
@Getter
@Setter
@NoArgsConstructor
public class DuenoMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDuenoMascota", nullable = false)
    private Integer idDuenoMascota;

    @Column(name = "id_Dueno")
    private Integer idDueno;

    @Column(name = "id_Mascota")
    private Integer idMascota;

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