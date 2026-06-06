package com.utp.sistemaclinicaveterinaria.modulos.Permiso;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Permiso")
@Getter
@Setter
@NoArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermiso", nullable = false)
    private Integer idPermiso;

    @Column(name = "nombrePermiso", nullable = false, length = 255)
    private String nombrePermiso;

    @Column(name = "descripcionPermiso", nullable = false, length = 255)
    private String descripcionPermiso;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}