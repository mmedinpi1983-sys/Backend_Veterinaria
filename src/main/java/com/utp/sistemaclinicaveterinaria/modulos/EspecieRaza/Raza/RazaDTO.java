package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Raza;

import java.time.LocalDateTime;

public class RazaDTO {
        record RazaCatalogResponse(
                        Integer idEspecieRaza,
                        String nombre,
                        Integer idEspecie) {
        }

        record RazaListResponse(
                        Integer idEspecieRaza,
                        String nombre,
                        String nombreEspecie,
                        Boolean estado,
                        LocalDateTime fechaCreacion) {
        }

        record RazaDetailResponse(
                        Integer idEspecieRaza,
                        String nombre,
                        Integer idEspecie,
                        String nombreEspecie,
                        Boolean estado,
                        String usuarioCreador,
                        LocalDateTime fechaCreacion,
                        String usuarioModificador,
                        LocalDateTime fechaModificacion,
                        String usuarioEliminador,
                        LocalDateTime fechaEliminacion) {
        }

        record RazaCreateRequest(
                        String nombre,
                        Integer idEspecie) {
        }

        record RazaUpdateRequest(
                        String nombre,
                        Integer idEspecie,
                        Boolean estado) {
        }

        record RazaFilterRequest(
                        String nombre,
                        Integer idEspecie,
                        Boolean estado) {
        }

        record RazaDeleteRequest(
                        Integer idRaza) {
        }
}