package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

import java.time.LocalDate;

// Proyección de una programación para el formulario de Nueva Cita.
// A diferencia de VeterinarioView (que agrupa por veterinario+turno), aquí cada fila es
// una programación concreta con su fecha y servicio, para poder filtrar en el front.
public interface ProgramacionCitaView {
    Integer getIdProgramacion();
    LocalDate getFecha();
    Integer getIdServicio();
    String getNombreServicio();
    String getNombreVeterinario();
    String getNombreTurno();
    String getHoraInicio();
    String getHoraFin();
}
