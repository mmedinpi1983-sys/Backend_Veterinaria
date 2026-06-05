package com.utp.sistemaclinicaveterinaria.modulos.Mascota;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Mascota")
@Getter
@Setter
@NoArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMascota", nullable = false)
    private Integer idMascota;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "id_Especie")
    private Integer idEspecie;

    @Column(name = "id_Raza")
    private Integer idRaza;

    @Column(name = "id_Asociado", nullable = false)
    private Integer idAsociado;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "sexo", length = 10)
    private String sexo;

    @Column(name = "tamanio", length = 20)
    private String tamanio;

    @Column(name = "notas", length = 500)
    private String notas;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

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