package com.utp.sistemaclinicaveterinaria.modulos.SuperAdmin;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SuperAdmin")
@Getter
@Setter
@NoArgsConstructor
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuper", nullable = false)
    private Integer idSuper;

    @Column(name = "correo", nullable = false, length = 500)
    private String correo;

    @Column(name = "contrasena", nullable = false, length = 500)
    private String contrasena;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;
}
