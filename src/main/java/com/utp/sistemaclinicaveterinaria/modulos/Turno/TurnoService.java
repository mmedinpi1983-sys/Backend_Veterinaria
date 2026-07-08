package com.utp.sistemaclinicaveterinaria.modulos.Turno;

import java.util.List;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Turno.TurnoDTO.TurnoUpdateRequest;

public interface TurnoService {

    List<TurnoCatalogResponse> catalogo(Integer idASociado);

    List<TurnoListResponse> listar(TurnoFilterRequest f);

    TurnoDetailResponse obtenerId(Integer idTurno, Integer idAsociado);

    void crear(TurnoCreateRequest c);

    void actualizar(Integer idTurno, TurnoUpdateRequest m);

    void eliminar(TurnoDeleteRequest e);
}
