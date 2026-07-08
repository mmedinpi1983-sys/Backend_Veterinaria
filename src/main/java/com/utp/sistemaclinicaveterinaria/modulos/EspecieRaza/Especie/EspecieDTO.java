package com.utp.sistemaclinicaveterinaria.modulos.EspecieRaza.Especie;

import java.time.LocalDateTime;

public class EspecieDTO {
        // OBJETO DE ESPECIE PARA CATALOGO
        record EspecieCatalogResponse(
                        Integer idEspecieRaza,
                        String nombre) {
        }

        // OBJETO DE ESPECIE PARA LISTA DE TABLA
        record EspecieListResponse(
                        Integer idEspecieRaza,
                        String nombre,
                        Boolean estado,
                        LocalDateTime FechaCreacion
                        ) {
        }

        // Objeto Completo para detalle de Especie
        record EspecieDetailResponse(
                        Integer idEspecieRaza,
                        String nombre,
                        Boolean estado,
                        String usuarioCreador,
                        LocalDateTime fechaCreacion,
                        String usuarioModificador,
                        LocalDateTime fechaModificacion,
                        String usuarioEliminador,
                        LocalDateTime fechaEliminacion) {
        }

        // objeto de create para especie
        record EspecieCreateRequest(
                        String nombre

                        ) {
        }

        // objeto de update para especie
        record EspecieUpdateRequest(
                        // Integer idEspecieRaza,
                        String nombre,
                        Boolean estado) {
        }

        // objeto de filtro para especie
        record EspecieFilterRequest(
                        String nombre,
                        Integer idRaza,
                        Boolean estado,
                        Integer idAsociado) {
        }

        // objeto para eliminar una especie
        record EspecieDeleteRequest(
                        Integer idEspecie,
                        Integer idUsuario,
                        Integer idAsociado) {
        }
}
