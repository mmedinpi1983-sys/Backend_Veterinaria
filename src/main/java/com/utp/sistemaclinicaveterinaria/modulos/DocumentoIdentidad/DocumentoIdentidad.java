package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DocumentoIdentidad")
@Getter
@Setter
@NoArgsConstructor
public class DocumentoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocumentoIdentidad", nullable = false)
    private Integer idDocumentoIdentidad;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

}