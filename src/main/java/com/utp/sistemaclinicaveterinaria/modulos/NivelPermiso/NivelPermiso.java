package com.utp.sistemaclinicaveterinaria.modulos.NivelPermiso;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Nivel_Permiso")
@Getter
@Setter
@NoArgsConstructor
public class NivelPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Nivel_Permiso", nullable = false)
    private Integer idNivelPermiso;

    @Column(name = "id_Permiso", nullable = false)
    private Integer idPermiso;

    @Column(name = "id_NivelSuscripcion", nullable = false)
    private Integer idNivelSuscripcion;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_SuperAdminCreador", nullable = false)
    private Integer idSuperAdminCreador;

    @Column(name = "id_SuperAdminModificador")
    private Integer idSuperAdminModificador;

    @Column(name = "id_SuperAdminEliminador")
    private Integer idSuperAdminEliminador;

}