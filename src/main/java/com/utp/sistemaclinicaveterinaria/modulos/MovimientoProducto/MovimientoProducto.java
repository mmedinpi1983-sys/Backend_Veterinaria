package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MovimientoProducto")
@Getter
@Setter
@NoArgsConstructor
public class MovimientoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento", nullable = false)
    private Integer idMovimiento;

    @Column(name = "id_Producto", nullable = false)
    private Integer idProducto;

    @Column(name = "id_ClaseMovimiento", nullable = false)
    private Integer idClaseMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "stockAnterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "stockNuevo", nullable = false)
    private Integer stockNuevo;

    @Column(name = "id_MotivoMovimiento", nullable = false)
    private Integer idMotivoMovimiento;

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;
}
