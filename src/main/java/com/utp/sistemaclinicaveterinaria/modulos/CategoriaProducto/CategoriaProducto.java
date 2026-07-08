package com.utp.sistemaclinicaveterinaria.modulos.CategoriaProducto;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CategoriaProducto")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoriaProducto", nullable = false)
    private Integer idCategoriaProducto;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "fechaCreada", nullable = false)
    private LocalDateTime fechaCreada;

    @Column(name = "fechaModificada")
    private LocalDateTime fechaModificada;

    @Column(name = "fechaEliminada")
    private LocalDateTime fechaEliminada;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;
}
