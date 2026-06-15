package com.utp.sistemaclinicaveterinaria.modulos.CitaProgramada;

// Proyección de un veterinario disponible para agendar: trae su programación (para enlazar la cita)
// y el horario de su turno (para filtrar las horas seleccionables en el modal de Nueva Cita).
public interface VeterinarioView {
    Integer getIdProgramacion();
    String getNombreVeterinario();
    String getNombreTurno();
    String getHoraInicio();
    String getHoraFin();
}
