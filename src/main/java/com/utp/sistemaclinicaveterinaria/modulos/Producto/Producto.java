package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private Integer idProducto;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "id_Categoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "fechaFabricacion", nullable = false)
    private LocalDate fechaFabricacion;

    @Column(name = "fechaVencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "lote", nullable = false, length = 500)
    private String lote;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "cantidadIngreso", nullable = false)
    private Integer cantidadIngreso;

    @Column(name = "precioCompra", precision = 18, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "concentracion", length = 500)
    private String concentracion;

    @Column(name = "precioVenta", precision = 18, scale = 2)
    private BigDecimal precioVenta;

    @Column(name = "requiereReceta", nullable = false)
    private Boolean requiereReceta;

    @Column(name = "proveedor", length = 500)
    private String proveedor;

    @Column(name = "id_Presentacion")
    private Integer idPresentacion;

    @Column(name = "cantidadMinima", nullable = false)
    private Integer cantidadMinima;

    @Column(name = "id_EmpleadoAsociado", nullable = false)
    private Integer idEmpleadoAsociado;

    @Column(name = "requiereInventario", nullable = false)
    private Boolean requiereInventario;

    @Column(name = "notas", length = 500)
    private String notas;

    @Column(name = "id_EmpleadoCreador", nullable = false)
    private Integer idEmpleadoCreador;

    @Column(name = "id_EmpleadoModificador")
    private Integer idEmpleadoModificador;

    @Column(name = "id_EmpleadoEliminador")
    private Integer idEmpleadoEliminador;
}
