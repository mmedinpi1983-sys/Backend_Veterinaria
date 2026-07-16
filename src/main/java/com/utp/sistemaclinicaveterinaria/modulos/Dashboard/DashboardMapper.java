package com.utp.sistemaclinicaveterinaria.modulos.Dashboard;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.DashboardDTO.AlertaStockResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.DashboardDTO.CitaSemanaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.DashboardDTO.ResumenResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.AlertaStockProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.CitaSemanaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.Projection.ResumenProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DashboardMapper {

    ResumenResponse toResumen(ResumenProjection p);

    CitaSemanaResponse toCitaSemana(CitaSemanaProjection p);

    List<CitaSemanaResponse> toCitaSemana(List<CitaSemanaProjection> p);

    AlertaStockResponse toAlertaStock(AlertaStockProjection p);

    List<AlertaStockResponse> toAlertaStock(List<AlertaStockProjection> p);
}
