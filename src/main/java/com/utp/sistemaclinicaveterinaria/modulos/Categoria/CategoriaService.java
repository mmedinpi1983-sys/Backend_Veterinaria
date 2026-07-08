package com.utp.sistemaclinicaveterinaria.modulos.Categoria;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Categoria.CategoriaDTO.CategoriaUpdateRequest;

public interface CategoriaService {
    List<CategoriaCatalogResponse> catalogo(Integer idAsociado);
    List<CategoriaListResponse> listar(CategoriaFilterRequest f);
    CategoriaDetailResponse obtenerId(Integer idCategoria, Integer idAsociado);
    void crear(CategoriaCreateRequest c);
    void actualizar(Integer idCategoria, CategoriaUpdateRequest mt);
    void eliminar(CategoriaDeleteRequest e);
}
