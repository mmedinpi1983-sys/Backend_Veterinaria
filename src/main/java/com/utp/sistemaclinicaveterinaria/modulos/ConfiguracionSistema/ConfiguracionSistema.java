package com.utp.sistemaclinicaveterinaria.modulos.ConfiguracionSistema;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ConfiguracionSistema")
@Getter
@Setter
@NoArgsConstructor
public class ConfiguracionSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConfiguracionSistema", nullable = false)
    private Integer idConfiguracionSistema;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "zonaHoraria", length = 100)
    private String zonaHoraria;

    @Column(name = "formatoFecha", length = 50)
    private String formatoFecha;

    @Column(name = "moneda", length = 50)
    private String moneda;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

}
