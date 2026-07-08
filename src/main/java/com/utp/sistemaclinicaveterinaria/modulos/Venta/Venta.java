package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Venta")
@Getter
@Setter
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta", nullable = false)
    private Integer idVenta;

    @Column(name = "codigoVenta", nullable = false, length = 500)
    private String codigoVenta;

    @Column(name = "id_Atencion")
    private Integer idAtencion;

    @Column(name = "id_Cita")
    private Integer idCita;

    @Column(name = "id_Dueno")
    private Integer idDueno;

    @Column(name = "tipoComprobante", length = 20)
    private String tipoComprobante;

    @Column(name = "fechaVenta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(name = "subTotal", precision = 18, scale = 2)
    private BigDecimal subTotal;

    @Column(name = "descuento", precision = 18, scale = 2)
    private BigDecimal descuento;

    @Column(name = "igv", precision = 18, scale = 2)
    private BigDecimal igv;

    @Column(name = "total", precision = 18, scale = 2)
    private BigDecimal total;

    @Column(name = "montoPagado", precision = 18, scale = 2)
    private BigDecimal montoPagado;

    @Column(name = "estadoVenta")
    private Integer estadoVenta;

    @Column(name = "id_MetodoPago", nullable = false)
    private Integer idMetodoPago;

    @Column(name = "id_EmpleadoAsociado", nullable = false)
    private Integer idEmpleadoAsociado;

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

    @Column(name = "dniComprador", length = 500)
    private String dniComprador;

    @Column(name = "nombreComprador", length = 500)
    private String nombreComprador;
}
