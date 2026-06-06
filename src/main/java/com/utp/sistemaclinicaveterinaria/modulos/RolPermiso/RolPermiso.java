package com.utp.sistemaclinicaveterinaria.modulos.RolPermiso;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Rol_Permiso")
@Getter
@Setter
@NoArgsConstructor
public class RolPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Rol_Permiso", nullable = false)
    private Integer idRolPermiso;

    @Column(name = "id_RolesClinica", nullable = false)
    private Integer idRolesClinica;

    @Column(name = "id_Permiso", nullable = false)
    private Integer idPermiso;

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