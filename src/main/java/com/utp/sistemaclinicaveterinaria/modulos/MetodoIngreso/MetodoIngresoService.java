package com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MetodoIngreso.MetodoIngresoDTO.MetodoIngresoUpdateRequest;

public interface MetodoIngresoService {
    List<MetodoIngresoCatalogResponse> catalogo(Integer idAsociado);
    List<MetodoIngresoListResponse> listar(Integer idAsociado);
    MetodoIngresoDetailResponse obtenerId(Integer idMetodoIngreso, Integer idAsociado);
    void crear(MetodoIngresoCreateRequest c);
    void actualizar(Integer idMetodoIngreso, MetodoIngresoUpdateRequest mt);
    void eliminar(MetodoIngresoDeleteRequest e);
}
