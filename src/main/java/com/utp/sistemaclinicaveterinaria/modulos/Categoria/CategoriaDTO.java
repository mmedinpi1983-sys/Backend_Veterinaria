package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import java.time.*;

public interface CategoriaDTO {

    record CategoriaCatalogResponse(
            Integer idCategoria,
            String nombreCategoria) {
    }

    record CategoriaListResponse(
            Integer idCategoria,
            String nombreCategoria,
            Boolean estado,
            LocalDateTime fechaCreacion) {
    }

    record CategoriaDetailResponse(
            Integer idCategoria,
            String nombreCategoria,
            Boolean estado,
            String usuarioCreador,
            LocalDateTime fechaCreacion,
            String usuarioModificador,
            LocalDateTime fechaModificacion,
            String usuarioEliminador,
            LocalDateTime fechaEliminacion) {
    }

    record CategoriaCreateRequest(
            String nombreCategoria) {
    }

    record CategoriaUpdateRequest(
            String nombreCategoria,
            Boolean estado) {
    }

    record CategoriaFilterRequest(
            String nombreCategoria,
            Boolean estado) {
    }

    record CategoriaDeleteRequest(
            Integer idCategoria) {
    }
}
