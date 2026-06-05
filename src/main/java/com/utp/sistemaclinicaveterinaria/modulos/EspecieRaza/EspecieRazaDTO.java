package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza;
import java.time.*;

import java.util.List;
public interface EspecieRazaDTO {
    record Request(
                 String nombre,
                 Integer idEspecie,
                 Boolean estado,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idEspecieRaza,
                 String nombre,
                 Integer idEspecie,
                 Boolean estado,
                 LocalDateTime fechaCreacion,
                 LocalDateTime fechaModificacion,
                 LocalDateTime fechaEliminacion,
                 Integer idAsociado,
                 Integer idEmpleadoCreador,
                 Integer idEmpleadoModificador,
                 Integer idEmpleadoEliminador
    ) {}
    record ListItem(
                 Integer idEspecieRaza,
                 String nombre,
                 Integer idEspecie,
                 LocalDateTime fechaCreacion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
