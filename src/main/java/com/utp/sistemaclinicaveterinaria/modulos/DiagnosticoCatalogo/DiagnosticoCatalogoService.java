package com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.DiagnosticoCatalogo.DiagnosticoCatalogoDTO.DiagnosticoCatalogoUpdateRequest;

public interface DiagnosticoCatalogoService {
    List<DiagnosticoCatalogoCatalogResponse> catalogo();
    List<DiagnosticoCatalogoListResponse> listar();
    DiagnosticoCatalogoDetailResponse obtenerId(Integer id);
    void crear(DiagnosticoCatalogoCreateRequest c);
    void actualizar(Integer id, DiagnosticoCatalogoUpdateRequest mt);
    void eliminar(DiagnosticoCatalogoDeleteRequest e);
}
