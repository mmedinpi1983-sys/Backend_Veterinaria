package com.utp.sistemaclinicaveterinaria.modulos.Atencion;
import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.AtencionUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Atencion.AtencionDTO.DetalleCompleto;

public interface AtencionService {
    List<AtencionListResponse> listar();
    AtencionDetailResponse obtenerId(Integer idAtencion);
    AtencionDetailResponse obtenerPorCita(Integer idCita);
    DetalleCompleto obtenerDetalle(Integer id);
    Integer crear(AtencionCreateRequest c);
    void actualizar(Integer idAtencion, AtencionUpdateRequest mt);
    void eliminar(AtencionDeleteRequest e);
    List<HistorialView> getHistorialByMascota(Integer idMascota);
}
