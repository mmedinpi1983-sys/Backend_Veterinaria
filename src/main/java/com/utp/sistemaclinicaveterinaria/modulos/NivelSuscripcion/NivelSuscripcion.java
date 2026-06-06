package com.utp.sistemaclinicaveterinaria.modulos.NivelSuscripcion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NivelSuscripcion")
@Getter
@Setter
@NoArgsConstructor
public class NivelSuscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNivel", nullable = false)
    private Integer idNivel;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "cantidadUsuario", nullable = false)
    private Integer cantidadUsuario;

    @Column(name = "precioMensual")
    private BigDecimal precioMensual;

    @Column(name = "precioAnual")
    private BigDecimal precioAnual;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "id_SuperAdminCreador", nullable = false)
    private Integer idSuperAdminCreador;

    @Column(name = "id_SuperAdminModificador")
    private Integer idSuperAdminModificador;

    @Column(name = "id_SuperAdminEliminador")
    private Integer idSuperAdminEliminador;

}